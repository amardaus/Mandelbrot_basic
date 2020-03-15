package complex;
import java.lang.Math;

public class Complex implements Field<Complex>, Cloneable{

    public double getRe() {
        return re;
    }

    public double getIm() {
        return im;
    }

    double re, im;

    Complex(){
        this.re = 0;
        this.im = 0;
    }

    public Complex(double re){
        this.re = re;
        this.im = 0;
    }

    public Complex(double re, double im){
        this.re = re;
        this.im = im;
    }

    Complex(Complex c){
        this.re = c.re;
        this.im = c.im;
    }


    double abs(){       // Modul
        return Math.sqrt(Math.pow(this.re, 2) + Math.pow(this.im, 2));
    };

    public double sqrAbs(){    // Kwadrat modulu
        return Math.pow(this.re, 2) + Math.pow(this.im, 2);
    };

    double re(){        // Czesc rzeczywista
        return this.re;
    };
    double im(){        // Czesc urojona
        return this.im;
    };

    double phase() {     //Faza
        if(this.re == 0){
            return 0.5*Math.PI*Math.signum(this.im);
        }
        else{
            if(this.re > 0){
                return Math.atan(this.im / this.re);
            }
            else{
                return Math.atan(this.im / this.re) + Math.PI;
            }
        }
    };
    Complex(String s){
        double r = 0;
        double i = 0;

        boolean firstSign = true;   //Sprawdzanie znaku czesci rzeczywistej
        boolean secondSign = true;    //Sprawdzanie znaku czesci urojonej
        if(s.charAt(0)=='-'){
            firstSign = false;
        }
        if(s.substring(1).contains("-")){
            secondSign = false;
        }
        String[] parts = s.split("[-+]");

        if(parts[0].equals("")){
            parts[0] = parts[1];
            parts[1] = parts[2];
        }

        //System.out.println(parts[0]);
        //System.out.println(parts[1]);

        if(parts[0].contains("i")){
            i = Double.parseDouble(parts[0].substring(0, parts[1].length()));
            i *= (firstSign ? 1 : -1);
        }
        else{
            r = Double.parseDouble(parts[0]);
            r *= (firstSign ? 1 : -1);
        }

        if(parts.length > 1){
            if(parts[1].contains("i")){
                i = Double.parseDouble(parts[1].substring(0, parts[1].length() - 1));
                i *= (secondSign ? 1 : -1);
            }
            else{
                r = Double.parseDouble(parts[1]);
                r *= (secondSign ? 1 : -1);
            }
        }
        this.im = i;
        this.re = r;
    };

    @Override
    public Complex add(Complex c){
        this.re = this.re + c.re;
        this.im = this.im + c.im;
        return this;
    };
    @Override
    public Complex sub(Complex c){
        this.re = this.re - c.re;
        this.im = this.im - c.im;
        return this;
    };
    @Override
    public Complex mul(Complex c){
        double _re = this.re * c.re - this.im * c.im;
        double _im = this.re * c.im + this.im * c.re;
        this.re = _re;
        this.im = _im;
        return this;
    };
    @Override
    public Complex div(Complex c) throws DivideByZeroException{
        if(c.re == 0 && c.im == 0){
            throw new DivideByZeroException();
        }

        else{
            double _re = (this.re * c.re + this.im * c.im)/(c.re * c.re + c.im * c.im);
            double _im = (c.re * this.im - c.im * this.re)/(c.re * c.re + c.im * c.im);
            this.re = _re;
            this.im = _im;
            return this;
        }
    };

    static Complex add(Complex c1, Complex c2){
        Complex c = new Complex();
        c.re = c1.re + c2.re;
        c.im = c1.im + c2.im;
        return c;
    };

    static Complex sub(Complex c1, Complex c2){
        Complex c = new Complex();
        c.re = c1.re - c2.re;
        c.im = c1.im - c2.im;
        return c;
    };

    static Complex mul(Complex c1, Complex c2){
        Complex c = new Complex();
        double _re = c1.re * c2.re - c1.im * c2.im;
        double _im = c1.re * c2.im + c1.im * c2.re;
        c.re = _re;
        c.im = _im;
        return c;
    }

    static Complex div(Complex c1, Complex c2) throws DivideByZeroException{
        if(c2.re == 0 && c2.im == 0) throw new DivideByZeroException();
        else{
            Complex complex = new Complex();
            double _re = (c1.re * c2.re + c1.im * c2.im)/(c2.re * c2.re + c2.im * c2.im);
            double _im = (c2.re * c1.im - c2.im * c1.re)/(c2.re * c2.re + c2.im * c2.im);
            complex.re = _re;
            complex.im = _im;
            return complex;
        }
    };

    @Override
    public Object clone() {
        Complex complex = null;
        try {
            complex = (Complex) super.clone();
        } catch (CloneNotSupportedException e) {
            complex = new Complex(
                    this.getRe(), this.getIm());
        }
        return complex;
    }

    static double abs(Complex c){
        return Math.sqrt(Math.pow(c.re, 2) + Math.pow(c.im, 2));
    };

    static double sqrAbs(Complex c){
        return Math.pow(c.re, 2) + Math.pow(c.im, 2);
    };

    static double phase(Complex c){
        if(c.re == 0){
            return 0.5*Math.PI*Math.signum(c.im);
        }
        else{
            if(c.re > 0){
                return Math.atan(c.im / c.re);
            }
            else{
                return Math.atan(c.im / c.re) + Math.PI;
            }
        }
    };

    static double re(Complex c){
        return c.re;
    };

    static double im(Complex c){
        return c.im;
    };

    public String toString(){
        return String.valueOf(this.re) + "+" + String.valueOf(this.im) + "i";
    }

    static Complex valueOf(String s){
        double r = 0;
        double i = 0;

        boolean firstSign = true;   //Sprawdzanie znaku czesci rzeczywistej
        boolean secondSign = true;    //Sprawdzanie znaku czesci urojonej
        if(s.charAt(0)=='-'){
            firstSign = false;
        }
        if(s.substring(1).contains("-")){
            secondSign = false;
        }
        String[] parts = s.split("[-+]");

        if(parts[0].equals("")){
            parts[0] = parts[1];
            parts[1] = parts[2];
        }

        //System.out.println(parts[0]);
        //System.out.println(parts[1]);

        if(parts[0].contains("i")){
            i = Double.parseDouble(parts[0].substring(0, parts[1].length()));
            i *= (firstSign ? 1 : -1);
        }
        else{
            r = Double.parseDouble(parts[0]);
            r *= (firstSign ? 1 : -1);
        }

        if(parts.length > 1){
            if(parts[1].contains("i")){
                i = Double.parseDouble(parts[1].substring(0, parts[1].length() - 1));
                i *= (secondSign ? 1 : -1);
            }
            else{
                r = Double.parseDouble(parts[1]);
                r *= (secondSign ? 1 : -1);
            }
        }
        return new Complex(r, i);
    };

    void setRe(double re){
        this.re = re;
    };

    void setIm(double im){
        this.im = im;
    };

    void setVal(Complex c){
        this.re = c.re;
        this.im = c.im;
    };

    void setVal(double re, double im){
        this.re = re;
        this.im = im;
    };

    public static void main(String[] args){
        Complex c1 = new Complex(3, 2.0);
        Complex c2 = new Complex(3, -2);
        //System.out.println(c1.mul(c2).toString());
        //System.out.println(mul(c1, c2).toString());

        try{
            div(c1, c2);
            //System.out.println(c1.div(c2).toString());
            //System.out.println(div(c1, c2).toString());
        }
        catch(DivideByZeroException e){
            System.out.println(e.getMessage());
        }

        valueOf("1i-2");

    }
}