import clarifai2.api.ClarifaiBuilder;
import clarifai2.api.ClarifaiClient;
import clarifai2.api.ClarifaiResponse;
import clarifai2.dto.input.ClarifaiInput;
import clarifai2.dto.input.image.ClarifaiImage;
import clarifai2.dto.model.output.ClarifaiOutput;
import clarifai2.dto.prediction.Concept;
import okhttp3.OkHttpClient;
import org.apache.commons.collections.ListUtils;
import org.openimaj.image.DisplayUtilities;
import org.openimaj.image.ImageUtilities;
import org.openimaj.image.MBFImage;

import java.io.File;
import java.io.IOException;
import java.util.List;


import static com.sun.org.apache.bcel.internal.classfile.Utility.printArray;

/**
 * Created by Naga on 24-01-2017.
 */

public class ImageAnnotation {
    public static void main(String[] args) throws IOException {
        final ClarifaiClient client = new ClarifaiBuilder("KKQIegBW9uOl_3vaMSzqq4QCfPNyNBvB7XNBz1vE", "xsY48eiDhhsFo5M7HE3F71ZYkB_tEQmemlWekTgG")
                .client(new OkHttpClient()) // OPTIONAL. Allows customization of OkHttp by the user
                .buildSync(); // or use .build() to get a Future<ClarifaiClient>
        client.getToken();

        String[][] arr=new String[50][50];
        File file = new File("output/mainframes");
        File log = new File("123.txt");

        File[] files = file.listFiles();
        for (int i=0; i<files.length;i++){
            ClarifaiResponse response = client.getDefaultModels().generalModel().predict()
                    .withInputs(
                            ClarifaiInput.forImage(ClarifaiImage.of(files[i]))
                    )
                    .executeSync();
            List<ClarifaiOutput<Concept>> predictions = (List<ClarifaiOutput<Concept>>) response.get();
            MBFImage image = ImageUtilities.readMBF(files[i]);
            int x = image.getWidth();
            int y = image.getHeight();

            System.out.println("*************" + files[i] + "***********");
             List<Concept> data = predictions.get(0).data();


            //System.out.println(data);

            String[] name=new String[data.size()];
            float[] value=new float[data.size()];
            for (int j = 0; j < data.size(); j++) {
               // System.out.println(data.get(j).name() + " - " + data.get(j).value());
                name[j] = data.get(j).name();
                value[j] = data.get(j).value();




            DisplayUtilities.displayName(image, "image" + i);
        }

            for(int k=0;k<6;k++)
            {
                arr[i][k]=data.get(k).name();
                System.out.println(arr[i][k]);
                System.out.println("\t");
            }


        }
    }
}
