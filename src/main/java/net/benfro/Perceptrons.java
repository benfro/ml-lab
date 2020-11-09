package net.benfro;

import java.util.List;
import com.google.common.collect.Lists;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.random.Well512a;
import org.apache.commons.rng.UniformRandomProvider;
import org.apache.commons.rng.simple.RandomSource;



public class Perceptrons {

   TwoDimArray<Double> trainInput = new TwoDimArray<>(1000);
   List<Integer> trainLabels = Lists.newArrayListWithExpectedSize(1000);

   TwoDimArray<Double> testInput = new TwoDimArray<>(200);
   List<Integer> testLabels = Lists.newArrayListWithExpectedSize(200);
   List<Integer> testPredicted = Lists.newArrayListWithExpectedSize(200);

   final int epochs = 2000;
   final double learningRate = 1.;

   NormalDistribution classOne = new NormalDistribution(new Well512a(System.nanoTime()), -2.0, 1.0);
   NormalDistribution classTwo = new NormalDistribution(new Well512a(System.nanoTime()),2.0, 1.0);
   private double[] w;

   public Perceptrons(int nIn) {
      w = new double[2];
      for (int i = 0; i < 500; i++) {
         trainInput.add(classOne.sample(), classTwo.sample());
         trainLabels.add(1);
         trainInput.add(classTwo.sample(), classOne.sample());
         trainLabels.add(-1);
      }
      int epoch = 0;
      while (true) {
         int classified_ = 0;

         for (int i=0; i < 1000; i++) {
            classified_ += train(trainInput.get(i), trainLabels.get(i), learningRate);
         }

         if (classified_ == 1000) break;  // when all data classified correctly

         epoch++;
         if (epoch > epochs) break;
      }


   }

   public int train(TwoDimArray.Row<Double> x, int t, double learningRate) {

      int classified = 0;
      double c = 0.;

      // check whether the data is classified correctly
      //for (int i = 0; i < dimensionsOfData; i++) {
         c += w[0]*x._1*t;
         c += w[1]*x._2*t;
      //}

      // apply gradient descent method if the data is wrongly classified
      if (c > 0) {
         classified = 1;
      } else {
         //for (int i = 0; i < dimensionsOfData; i++) {
            w[0] += learningRate*x._1*t;
            w[1] += learningRate*x._2*t;
         //}
      }

      return classified;
   }

   }
