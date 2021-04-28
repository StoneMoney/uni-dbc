public class MatchingData {
   private int id;
   private String displayString;
   public MatchingData(int id, String displayString) {
      this.id = id;
      this.displayString = displayString;
   }
   public int getId() {
      return id;
   }
   public String getDisplayString() {
      return displayString;
   }
   public String toString() {
      return displayString;
   }
}