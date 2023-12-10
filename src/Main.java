import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class Main {
    public static int floor, room, selection, max, min;
    public static Scanner cin = new Scanner(System.in);
    static String[][] arr;
    public static void main(String[] args) {

        Scanner cin = new Scanner(System.in);

        System.out.println("---------- Setting up Guest’s House ----------");
        floor = validateNumber("-> Enter number of floors: ", 1, 100);
        room = validateNumber("-> Enter number of rooms in each floor: ", 1, 100);
        arr = new String[floor][room];
        hoteloptions();

    }
    public static void hoteloptions(){
        System.out.println("=> Guest’s House is already set up with " + floor + " flo1ors and " + (floor*room) + " rooms successfully.");
        do {
            System.out.println("---------- Guest’s House Management System ----------");
            System.out.println("1- Check In");
            System.out.println("2- Check Out");
            System.out.println("3- Display");
            System.out.println("4- Search Guest’s Name");
            System.out.println("5- Exit");
            System.out.println("---------------------------------------------");
            do {
                selection = validateOption("-> Choose option(1-5) :", 1, 5);
            } while (!(selection > 0 && selection < 6));

            switch (selection) {
                case 1:
                    System.out.println("---------- Checkin to Guest’s House ----------");
                    CheckIn();
                    break;
                case 2:
                    System.out.println("---------- Checkout to Guest’s House ----------");
                    CheckOut();
                    break;
                case 3:
                    System.out.println("---------- Display Guest’s House information ----------");
                    Display();
                    break;
                case 4:
                    System.out.println("---------- Search Guest’s Name ----------");
                    Search();
                    break;
                case 5:
                    System.out.println("---------- Exit ----------");
                    System.exit(0);
                    break;
                default:
                    break;
            }//end switch

            System.out.println("(Press key e to exit, Press key Enter to continue)");
            String end = cin.nextLine();
            //when press key e exit
//            if (end.equalsIgnoreCase("e")) {
//                System.exit(0);
//            } else{
//                cin.nextLine();break;
//            }


        } while (true);
    }
    public static void CheckIn(){
        String name;
        int floors, rooms;
        Scanner cin = new Scanner(System.in);
        floors = validateCheckIn("-> Enter floor number(1-"+floor+") : ", 1, floor);
        rooms = validateCheckIn("-> Enter room number(1-"+(floor*room)+") : ", 1, floor*room);
        if(arr[floors-1][rooms-1] == null)
        {
            name = validateString("-> Enter guest's name : ");
            arr[floors-1][rooms-1] = name;
            System.out.println("=> "+ arr[floors-1][rooms-1] +" has been checked out successfully!");
        }
        else
            System.out.println("This room is already checked in, Please find another room! ");
    }

    public static void CheckOut(){
        int floors, rooms;
        floors = validateCheckIn("-> Enter floor number(1-"+floor+") : ", 1, floor);
        rooms = validateCheckIn("-> Enter room number(1-"+(floor*room)+") : ", 1, floor*room);
        if(arr[floors-1][rooms-1] == null)
        {
            System.out.println("No one checkin in this room, you can't checkout!");
        }
        else
        {
//            selection = validateOption("-> Guest's Name: "+ arr[floors-1][rooms-1] +", Press 1 to checkout and 0 to cancel: ", 0, 1);
            System.out.println("-> Guest's Name: "+ arr[floors-1][rooms-1] +", Press 1 to checkout and 0 to cancel: ");

            switch(selection)
            {
                case 1:
                    System.out.println("=> "+ arr[floors-1][rooms-1] +" has been checked out successfully!");
                    arr[floors-1][rooms-1] = null;
                    break;
                default:
                    break;
            }
        }
    }
//    public static void Display(){
//        Scanner myInput = new Scanner(System.in);
//
//        CellStyle numberStyle = new CellStyle(CellStyle.HorizontalAlign.right);
//        Table t = new Table(floor+1, BorderStyle.UNICODE_BOX, ShownBorders.ALL);
//
//        int perPage = 3;
//        int totalPage;
//        if(arr.length%perPage != 0){
//            totalPage = (arr.length/perPage) + 1;
//            //eg: arr.length=13 and when we jek jmuy perpage=3, it's gonna get 4 pages but somnol 1 will be missed if we dont create a page for it
//        }else {
//            //else oy vea smer totalPage thomada
//            totalPage = (arr.length/perPage);
//        }
//        int page = 1;   //every pages got a page lol
//        int choose = 0;
//        do{
//            int tmpRow = (page*perPage)-perPage;
//            // Length of Pages : if it's gonna have one page or more
//            if(page != totalPage){
//                floor = page * perPage;
//            }else{
//                floor = arr.length;
//            }
//            //eg: if totalPage=5, page=1 then lengthPg=5*1; else totalPage=1, page=1 then lengthPg=1;
//
//            //table
//            for(int row=tmpRow; row<floor; row++){
//                t.addCell( "Floor : "+ (row+1) + " ");
//                for(int col=0; col<room; col++){
//                    t.addCell(arr[row][col] + " ");
//                }
//                System.out.println("(Page "+page+")");
//                System.out.println();
//            }
//            System.out.println(t.render());
//            //ends table
//            choose = validateOption("1.Next     2.Next Page     3.Previous     4.Last Page     5.goto      6. Back  :",1,6);
//            switch (choose){
//                case 1:
//                    if (page<totalPage)
//                    page++;
//                    break;
//                case 2:
//                    if (page>=1)
//                    page--;
//                    break;
//                case 3:
//                    page = 1;
//                    break;
//                case 4:
//                    page = totalPage;
//                    break;
//                case 5:
//                    page=cin.nextInt();
//                case 6:
//                    choose = 0;
//                    break;
//            }
//
//        }while (choose != 0);
//        hoteloptions();
//    }

    public static void Display(){
        CellStyle numberStyle = new CellStyle(CellStyle.HorizontalAlign.right);

        Table t = new Table(floor+1, BorderStyle.UNICODE_BOX, ShownBorders.ALL);

        for(int i=0; i<floor;i++){
            t.addCell("Floor "+(i+1)+" ");
            for(int j=0; j<room; j++){
                t.addCell(arr[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println(t.render());
    }
//    public static void Display(){
//        for (int j=0; j<arr.length; j++){ //it could be floor since arr.length count how many row
//            System.out.print("Floor "+(j+1)+" :\t");
//            for(int i=0; i<arr[j].length; i++){ //it could be room since arr[j].length count how many collumns in a row
//                System.out.print(arr[j][i]+ "\t");
//            }
//            System.out.println();
//        }
//
//    }
    public static void Search(){

        String name = validateString("-> Enter guest's name : ");

        boolean search = false;
        for (int i=0; i < arr.length; i++){
            for (int j=0; j< arr[0].length; j++){
                if(arr[i][j] != null){

                    if(arr[i][j].equalsIgnoreCase(name)) {
                        System.out.println("Guest's Name : " + arr[i][j] + " is in Room : '" + (j + 1) + "' On Floor : '" + (i + 1) + "'");
                        search = true;
                        break;
                    }
                }
            }
        }
        if(!search){
            System.out.println("Guest : "+ name +" Not Stay Here!");
        }

    }
    static int validateOption(String ouputtext,int min,int max){
        Scanner cin = new Scanner(System.in);
        Pattern pattern = Pattern.compile("\\d+");
        boolean check;
        String inputext;
        do{
            System.out.print(ouputtext);
            inputext = cin.nextLine();
            check = pattern.matcher(inputext).matches()?(Integer.valueOf(inputext)>=min && Integer.valueOf(inputext)<=max):false;
            if(!check)
                System.out.println("Please read option's objective again!");
        }while (!check);
        return Integer.valueOf(inputext);
    }
    static int validateNumber(String ouputtext,int min,int max){
        Scanner cin = new Scanner(System.in);
        Pattern pattern = Pattern.compile("\\d+");
        boolean check;
        String inputext;
        do{
            System.out.print(ouputtext);
            inputext = cin.nextLine();

//            check = pattern.matcher(inputext).matches()?(Integer.valueOf(inputext)>=min && Integer.valueOf(inputext)<=max):false;
            if(pattern.matcher(inputext).matches()){
                check = (Integer.valueOf(inputext)>=min && Integer.valueOf(inputext)<=max);
            }else
                check=false;

            if(!check)
                System.out.println("(Input Number Only)");
        }while (!check);
        return Integer.valueOf(inputext);
    }
    static int validateCheckIn(String ouputtext,int min,int max){

        Scanner cin = new Scanner(System.in);
        Pattern pattern = Pattern.compile("\\d+");
        boolean check;
        String inputext;
        do{
            System.out.print(ouputtext);
            inputext = cin.nextLine();
            check = pattern.matcher(inputext).matches()?(Integer.valueOf(inputext)>=min && Integer.valueOf(inputext)<=max):false;
            if(!check)
                System.out.println("(This floor/room doesn't exit)");
        }while (!check);
        return Integer.valueOf(inputext);
    }
    static String validateString(String ouputtext){
        Scanner cin = new Scanner(System.in);
        Pattern pattern = Pattern.compile("\\w+");
        boolean check;
        String inputext;
        do{
            System.out.print(ouputtext);
            inputext = cin.nextLine();
            check = pattern.matcher(inputext).matches();
            if(!check)
                System.out.println("(Input Texts Only)");
        }while (!check);
        return String.valueOf(inputext);
    }
}
