package evaluation;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import org.dmg.pmml.FieldName;
import org.jpmml.evaluator.Evaluator;
import org.jpmml.evaluator.FieldValue;
import org.jpmml.evaluator.InputField;
import org.jpmml.evaluator.TargetField;

import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by root on 10/7/17.
 */

public class ModelEvaluator implements ModelEvaluation{
    public ModelEvaluator(){}

    String result=new String();
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public String modelEvaluationProcess(InputStream inputStream,String s) throws Exception {

        //Creating Evaluator for model
        Evaluator evaluator = createEvaluator(inputStream);

        //
        Map<FieldName, FieldValue> arguments = new LinkedHashMap<FieldName, FieldValue>();

        List<InputField> inputFields = evaluator.getInputFields();
        for (InputField inputField : inputFields) {
            FieldName inputFieldName = inputField.getName();

            // The raw (ie. user-supplied) value could be any Java primitive value
            Object rawValue = Double.valueOf(s);

            // The raw value is passed through: 1) outlier treatment, 2) missing value treatment, 3) invalid value treatment and 4) type conversion
            FieldValue inputFieldValue = inputField.prepare(rawValue);

            arguments.put(inputFieldName, inputFieldValue);
        }

        Map<FieldName, ?> results = evaluator.evaluate(arguments);

        List<TargetField> targetFields = evaluator.getTargetFields();
        for (TargetField targetField : targetFields) {
            FieldName targetFieldName = targetField.getName();
            //  System.out.print(targetFieldName.toString());
            result += targetFieldName.toString() + ":";
            Object targetFieldValue = results.get(targetFieldName);
            //   System.out.println(targetFieldValue.toString());
            result += targetFieldValue.toString();
        }

        Log.d("Modle:", evaluator.getSummary());

        return result;
    }

    @Override
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public Evaluator createEvaluator(InputStream input) throws Exception {
        try (InputStream is = input) {
            return EvaluatorUtil.createEvaluator(is);
        }

    }



}
