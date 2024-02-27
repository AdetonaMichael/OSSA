package remonode.ossa.curve;

public interface Curve {

    void calculateChainage(int chainageType, double chainageValue);

    void calculateLongChord(double radius, double deflectionAngle);

    void calculateExternalDistance(double radius, double deflectionAngle);

    void calculateCurveLength(double radius, double deflectionAngle);

    void calculateMidOrdinate(double radius, double deflectionAngle);


}
