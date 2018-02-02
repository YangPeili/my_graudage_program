/**
 * Created by yang on 2018/1/18.
 */

import model.ModelGenerate;
import mongodb.BasicOpt;

import java.util.List;

public class Test {

    public static void main(String[] args) throws Exception {
        List<String> list= ModelGenerate.generateModelsOnlyKonwParent("AlextNet");
//        List<String> list=ModelGenerate.generateSimpleVersionInfo("AlextNet");
//        List<String> collNameList=new ArrayList<String>();
//        collNameList.add("modelBasicInfo");
//        collNameList.add("model");
//        double s=System.currentTimeMillis();
//        for(int i=0;i<list.size();i++){
//            System.out.println(list.get(i));
//            if(i%2==0){
//               // BasicOpt.inser(list.get(i),collNameList.get(0));
//            }else{
//                BasicOpt.inser(list.get(i),collNameList.get(1));
//                //saveFileIntoGFS("C:\\Users\\yang\\Downloads\\d1enumerations\\auto-modeler-cifar10-models\\model-0\\snapshots\\snapshot_iter_25000.solverstate.h5");
//            }
//         }
//         double e=System.currentTimeMillis();
//        System.out.println((e-s)+"ms");

        for (int i=0;i<list.size();i++){

            BasicOpt.inser(list.get(i),"parentModel");
        }

    }
}

