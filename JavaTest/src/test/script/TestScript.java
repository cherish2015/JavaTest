package test.script;

import java.io.FileReader;

import javax.script.Bindings;
import javax.script.Invocable;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.junit.Test;

public class TestScript {

	public static void main(String[] args) {
	}
	
	@Test
	public void test(){
		ScriptEngine engine = new ScriptEngineManager().getEngineByName("javascript");
		Bindings bind = engine.createBindings();
		bind.put("factor", 2);
		engine.setBindings(bind, ScriptContext.ENGINE_SCOPE);
		int first = 20;
		int sec = 10;
		try {
			engine.eval(new FileReader("cal.js"));
			if (engine instanceof Invocable) {
				Invocable in = (Invocable)engine;
				Double result = (Double)in.invokeFunction("cal", first,sec);
				System.out.println(result);
			}
		}  catch (Exception e) {
			e.printStackTrace();
		}
	}

}
