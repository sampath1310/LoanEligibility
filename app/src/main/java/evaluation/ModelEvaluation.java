package evaluation;

import android.os.Build;
import android.support.annotation.RequiresApi;

import org.jpmml.evaluator.Evaluator;

import java.io.InputStream;

/**
 * Created by root on 10/7/17.
 */

public interface ModelEvaluation {


    String modelEvaluationProcess(InputStream inputStream,String s) throws Exception;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    Evaluator createEvaluator(InputStream input) throws Exception;

}
