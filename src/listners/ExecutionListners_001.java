package listners;

import org.testng.IExecutionListener;

public class ExecutionListners_001 implements IExecutionListener{

	@Override
	public void onExecutionStart(){
		System.out.println("TestNG is Starting...");
	}
	
	@Override
	public void onExecutionFinish(){
		System.out.println("TestNG has Finished");
	}
}
