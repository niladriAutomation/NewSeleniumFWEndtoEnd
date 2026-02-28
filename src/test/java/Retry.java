import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
    int start = 0;
    int end = 1;
    @Override
    public boolean retry(ITestResult result) {
        if(start<end){
            start+=1;
            return true;
        }

        return false;
    }
}
