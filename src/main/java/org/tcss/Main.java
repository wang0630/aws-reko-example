package org.tcss;

import org.tcss.DetectLabels;

public class Main {
  public static void main(String[] args) {
    if (args.length == 0) {
      System.out.println("File path must be provided!");
      return;
    }

    DetectLabels detectLabels = new DetectLabels();
    // "/Users/tsungjui/Desktop/test.JPG"
    detectLabels.detectLabels(args[0]);
  }
}
