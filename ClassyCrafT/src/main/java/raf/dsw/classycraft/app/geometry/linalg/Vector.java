package raf.dsw.classycraft.app.geometry.linalg;

import lombok.Getter;

import java.awt.*;

public class Vector {
    @Getter
    private final double x, y;
    public Vector(double x, double y)
    {
        this.x = x;
        this.y = y;
    }
    public Vector(Point start, Point end)
    {
        this.x = end.getX() - start.getX();
        this.y = end.getY() - start.getY();
    }
    public double getNorm()
    {
        return Math.sqrt(x * x + y * y);
    }
    public Vector normalized()
    {
        double norm = getNorm();
        return new Vector(x / norm, y / norm);
    }
    public Vector add(Vector other)
    {
        return new Vector(x + other.x, y + other.y);
    }
    public Vector sub(Vector other)
    {
        return new Vector(x - other.x, y - other.y);
    }
    public Vector mul(double scalar)
    {
        return new Vector(x * scalar, y * scalar);
    }
    public Vector div(double scalar)
    {
        return new Vector(x / scalar, y / scalar);
    }
    public double dot(Vector other)
    {
        return x * other.x + y * other.y;
    }
    public Vector getOrthogonal()
    {
        return new Vector(-y, x);
    }

}
