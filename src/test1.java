/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Tan
 */
import java.util.Scanner;
public class test1
{
    public static void main(String[] args)
    {
        int num1, num2, num3, result, temp;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập vào số thứ nhất:");
        num1 = scanner.nextInt();
        System.out.println("Nhập vào số thứ hai:");
        num2 = scanner.nextInt();
        System.out.println("Nhập vào số thứ ba:");
        num3 = scanner.nextInt();
        scanner.close();
        temp = num1 < num2 ? num1:num2;
        result = num3 < temp ? num3:temp;
        System.out.println("Số nhỏ nhất là: "+result);
    }
}