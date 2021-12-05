package bsu.rfe.java.group7.lab1.Lihimovich.varB10;

import javax.swing.table.AbstractTableModel;

public class GornerTableModel extends AbstractTableModel {
    private Double[] coefficients;
    private Double from;
    private Double to;
    private Double step;

    public GornerTableModel(Double from, Double to, Double step, Double[] coefficients) {
        this.from = from;
        this.to = to;
        this.step = step;
        this.coefficients = coefficients;
    }

    public Double getFrom() {
        return from;
    }
    public Double getTo() {
        return to;
    }
    public Double getStep() {
        return step;
    }
    public int getColumnCount() {
        return 3;
    }
    public int getRowCount() {
        return new Double(Math.ceil((to-from)/step)).intValue()+1;
    }

    public Object getValueAt(int row, int col) {
// Вычислить значение X как НАЧАЛО_ОТРЕЗКА + ШАГ*НОМЕР_СТРОКИ
        double x = from + step * row;

        double result = coefficients[0];
        for (int i = 1; i < coefficients.length; ++i)
            result = result * x + coefficients[i];

        switch(col) {
            case 0: {
                return x;
            }
            // Если запрашивается значение 2-го столбца, то это значение
            // многочлена
            case 1: {
                return result;
            }
            // Если запрашивается значение 3-го столбца, то проверяем на палиндром
            case 2: {
                String parts[] = (Double.toString(result)).split("\\.");
                if(parts.length == 2) {
                    if (parts[0].compareTo(parts[1])==0) return true;
                    else return false;
                }else return 0;
            }
            default: return 0.0;
        }
    }

    public String getColumnName(int col) {
        switch (col) {
            case 0:
// Название 1-го столбца
                return "Значение X";
            case 1:
// Название 2-го столбца
                return "Значение многочлена";
            case 2:
                return "Ограниченная симметрия";
            default:
                return "";
        }
    }

    public Class<?> getColumnClass(int col) {
// И в 1-ом и во 2-ом столбце находятся значения типа Double
        if(col==2) return Boolean.class;
        else return Double.class;
    }
}
