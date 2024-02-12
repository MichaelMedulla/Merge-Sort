import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class App 
{
    public static int[] generateRandomArray(int size)
    {
        int[] arr = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++)
        {
            arr[i] = random.nextInt(100);
        }
        return arr;
    }

    public static void swap(int[] arr, int i, int j)
    {
        if (arr == null || i < 0 || j < 0 || i >= arr.length || j >= arr.length)
        {
            return;
        }
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void mergeSort(int[] arr, int left, int right)
    {
        if(right - left > 1)
        {
            int mid = (left + right)/2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid, right);
            merge(arr, left, mid, right);
        }
    }

    public static void merge(int[] arr, int left, int mid, int right)
    {
        int[] leftArr = Arrays.copyOfRange(arr, left, mid);
        int[] rightArr = Arrays.copyOfRange(arr, mid, right);
        int i = 0, j = 0;

        for (int k = left; k < right; k++)
        {
            if(i == leftArr.length)
            {
                arr[k] = rightArr[j++];
            }
            else if(j == rightArr.length)
            {
                arr[k] = leftArr[i++];
            }
            else if(leftArr[i] <= rightArr[j])
            {
                arr[k] = leftArr[i++];
            }
            else
            {
                arr[k] = rightArr[j++];
            }
        }
    }

    public static void mergeSort(int[] arr)
    {
        mergeSort(arr, 0, arr.length);
    }

    public static void writeArrayToFile(int[] arr, String fp) throws IOException
    {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fp));

        for(int i = 0; i < arr.length; i++)
        {
            writer.write(Integer.toString(arr[i]));
            writer.newLine();
        }
        writer.close();
    }

    public static int[] readFileToArray(String fp) throws IOException
    {
        int[] arr = null;
        int count = 0;

        BufferedReader reader = new BufferedReader(new FileReader(fp));
        while(reader.readLine() != null)
        {
            count++;
        }
        reader.close();

        reader = new BufferedReader(new FileReader(fp));
        arr = new int[count];
        
        String line;
        int i = 0;

        while ((line = reader.readLine()) != null)
        {
            int num = Integer.parseInt(line.trim());
            arr[i++] = num;
        }
        reader.close();
        return arr;
    }

    public static void main(String[] args) throws Exception 
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Would you like to generate a random array(1) or read from a file(2)");
        Integer input = scan.nextInt();

        if (input == 1)
        {
            int[] arr = generateRandomArray(10);

            System.out.println("Array before sorting:");
            System.out.println(Arrays.toString(arr));

            mergeSort(arr);
            System.out.println("Array after sorting: ");
            System.out.println(Arrays.toString(arr));

            String fp = "array.txt";
            writeArrayToFile(arr, fp);
        }
        else if (input == 2)
        {
            Scanner scanner = new Scanner(System.in);
            System.out.println("What is the file name?");
            String fileName = scanner.nextLine();

            String fp = fileName;
            int[] arr = readFileToArray(fp);
            for(int num : arr)
            {
                System.out.println(num);
            }
            mergeSort(arr);
            System.out.println("Array after sorting: ");
            System.out.println(Arrays.toString(arr));

            String fp2 = "array2.txt";
            writeArrayToFile(arr, fp2);
            scanner.close();
        }
        else if(input != 1 && input != 2)
        {
            System.out.println("Invalid input, please try again");
        }
        scan.close();     
    }
}
