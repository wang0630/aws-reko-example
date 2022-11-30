package org.tcss;

import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.rekognition.RekognitionClient;
import software.amazon.awssdk.services.rekognition.model.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class DetectLabels {

  public void detectLabels(String imagePath) {
    try {
      InputStream sourceStream = new FileInputStream(imagePath);
      SdkBytes bytes = SdkBytes.fromInputStream(sourceStream);

      Image image = Image.builder().bytes(bytes).build();

      System.out.println(imagePath);

      try (RekognitionClient rekoClient = this.createClient();) {
        DetectLabelsRequest detectLabelsRequest
            = DetectLabelsRequest.builder()
            .image(image)
            .maxLabels(5)
            .minConfidence((float) 0.7)
            .build();

        DetectLabelsResponse response = rekoClient.detectLabels(detectLabelsRequest);

        int index = 0;
        for (Label label: response.labels()) {
          System.out.println(
              index + "-th: " + "Name of the label: " + label.name() + "\n" +
                  "Confidence score of the label: " + label.confidence() + "\n"
          );

          index++;
        }

      }
    } catch (FileNotFoundException  e) {
      System.out.println("FileNotFoundException: " + e.getMessage());
    } catch (RekognitionException e) {
      System.out.println("RekognitionException: " + e.getMessage());
    }
  }

  private RekognitionClient createClient() {
    return RekognitionClient.create();
  }
}
