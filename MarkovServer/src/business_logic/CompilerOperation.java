package business_logic;

import java.util.HashMap;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class CompilerOperation extends BaseOperation {

	@Override
	public Object execute(HashMap<Object, Object> map) {
		String sourceCode = (String) map.get("code");
		
		URL resourceURL = getClass().getClassLoader().getResource("."); //get the folder to save the Main.java file.
		
		File sourceFile = new File(resourceURL.getPath() + File.separator + "Main.java"); //reference to Main.java file.
		try {
			FileOutputStream sourceFileOutputStream = new FileOutputStream(sourceFile);
			sourceFileOutputStream.write(sourceCode.getBytes()); //write to the Main.java file.
			sourceFileOutputStream.close();
			
			JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler(); //get the system's java compiler (can be null if JDK is not properly configured).
			if (javaCompiler == null) {
				System.err.println("Java compiler not configured");
				return "Java compiler not configured on server";
			}
			//setup error PrintStream:
			ByteArrayOutputStream errorByteArrayOutputStream = new ByteArrayOutputStream();
			PrintStream errorPrintStream = new PrintStream(errorByteArrayOutputStream);
			int compileResult = javaCompiler.run(null, null, errorPrintStream, sourceFile.getAbsolutePath()); //compile the Main.java file.
			if (compileResult != 0) {
				String errorMessage = new String(errorByteArrayOutputStream.toByteArray());
				return new String("Syntax Error");
			}
			
			//Dynamically invoke the Main.main method:
			URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] {resourceURL});
			Class<?> clazz = Class.forName("Main", true, classLoader);
			Method mainMethod = clazz.getMethod("main", String[].class);
			
			PrintStream systemOut = System.out; //backup of System.out.
			//create a new PrintStream to capture the output of Main.main():
	        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	        PrintStream captureOut = new PrintStream(byteArrayOutputStream);
	        System.setOut(captureOut);
	        
	        mainMethod.invoke(null, new Object[] {null}); //invoke Main.main method.
	        
	        System.setOut(systemOut); //set the default System.out.
	        
	        return new String(byteArrayOutputStream.toByteArray()); //return the captured output.
		} catch (IOException | ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
			return e.toString();
		}
	}

}
