package net.benfro;

import java.util.List;
import com.google.common.collect.Lists;

public class TwoDimArray<T> {

   static class Row<T> {
      public final T _1;
      public final T _2;

      public Row(T _1, T _2) {
         this._1 = _1;
         this._2 = _2;
      }
   }

   final List<Row<T>> data;

   public TwoDimArray() {
      data = Lists.newArrayList();
   }

   public TwoDimArray(int size) {
      data = Lists.newArrayListWithCapacity(size);
   }

   public void add(T one, T two) {
      data.add(new Row<>(one, two));
   }

   public Row<T> get(int idx) {
      return data.get(idx);
   }


}
