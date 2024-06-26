package remonode.ossa.curve;

import remonode.ossa.momento.Editor;
import remonode.ossa.momento.History;

/**
 * SimpleCurve
 * With Undo Functionality to track state of previous coordinate
 */
public class SimpleCurve implements Curve {

    public double radius;
    public double deflectionAngle;
    public double tangentLength;
    public double longChord;
    public double externalDistance;
    public double curveLength;
    public double midOrdinate;
    public double chainageAtIntersectionPoint;
    public double chainageAtPointTangent;
    public double chainageAtPointCurve;

    Editor  editor = new Editor();
    History history = new History();

    public SimpleCurve(double radiusInput, double deflectionAngleInput){
        radius = radiusInput;
        deflectionAngle = deflectionAngleInput;
        tangentLength = radiusInput*Math.tan(Math.toRadians(deflectionAngleInput/2));
        editor.setContent("Simple Curve Calculated");
        history.push(editor.createState());
    }

    public void calculateMidOrdinate(double radiusInput, double deflectionAngleInput){
        midOrdinate = radiusInput*(1-Math.cos(Math.toRadians(deflectionAngleInput/2)));
        editor.setContent("Mid-Ordinate Calculated.");
        history.push(editor.createState());
    }

    public void calculateLongChord(double radiusInput, double deflectionAngleInput){
        longChord = 2*radiusInput*(Math.sin(Math.toRadians(deflectionAngleInput/2)));
        editor.setContent("Longchord Calculated");
        history.push(editor.createState());
    }

    public void calculateExternalDistance(double radiusInput, double deflectionAngleInput){
        externalDistance = radiusInput *(Math.cosh(Math.toRadians(deflectionAngleInput/2))-1);
        editor.setContent("External Distance Calculated");
        history.push(editor.createState());
    }

    public void calculateCurveLength(double radiusInput, double deflectionAngleInput){
        curveLength = (deflectionAngleInput* 2*Math.PI*radiusInput)/360;
        editor.setContent("CurveLength Calculated");
        history.push(editor.createState());
    }

    public void showCurve(){
        System.out.printf("Radius: %.3f\n",radius);
        System.out.printf("Tangent Length: %.3f\n",tangentLength);
        System.out.printf("Deflection Angle: %.3f\n", deflectionAngle);
        System.out.printf("Long Chord: %.3f\n",longChord);
        System.out.printf("External Distance: %.3f\n", externalDistance);
        System.out.printf("Curve Length: %.3f\n", curveLength);
        System.out.println("\n");
        System.out.printf("Chainage At Intersection: %.3f\n",chainageAtIntersectionPoint);
        System.out.printf("Chainage At Point-Tangent: %.3f\n",chainageAtPointTangent);
        System.out.printf("Chainage At Point-Curve: %.3f\n",chainageAtPointCurve);
        System.out.println(editor.getContent());
    }

    public void calculateChainage(int chainageType, double chainageValue){
        switch(chainageType){
            case 0:
                chainageAtIntersectionPoint = chainageValue;
                chainageAtPointCurve = chainageValue - tangentLength;
                chainageAtPointTangent = chainageAtPointCurve + curveLength;
                break;
            case 1:
                chainageAtPointCurve = chainageValue;
                chainageAtIntersectionPoint = chainageValue + tangentLength;
                chainageAtPointTangent = chainageValue + curveLength;
                break;
            case 2:
                chainageAtPointTangent = chainageValue;
                chainageAtPointCurve = chainageValue - curveLength;
                chainageAtIntersectionPoint = chainageAtPointCurve + tangentLength;
                break;
            default:
                break;
        }
    }




}
