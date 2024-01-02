import java.util.Scanner;

public class CurveCalculate {
    public static void main(String [] args){
        Scanner input  = new Scanner(System.in);
        System.out.println("Please Provide Chainage Information in Kilo Meters e.g 2310.020: ");
        System.out.println("Enter 0 For Chainage At Intersection Point ");
        System.out.println("Enter 1 For Chainage At Point Curve Point ");
        System.out.println("Enter 2 For Chainage At Point Tangent Point \n");
        
        System.out.println("Enter Chainage Type: ");
        int chainageType =  input.nextInt();
        System.out.println("Enter Chainage Value: ");
        double chainageValue = input.nextDouble();
        System.out.println("\n");
        input.close();

        SimpleCurve curve =  new SimpleCurve(200, 30);
        curve.calculateChainage(chainageType, chainageValue);
        curve.showCurve();
    }
}
