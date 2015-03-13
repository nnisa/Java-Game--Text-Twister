//Team: Ahmed Hashmi - Noshin Nisa
//IDs: arhashmi - nnsia
//Hours Worked: 18

import java.util.*;

public class TextTwister
{
  private Map<String, ArrayList<String>> map;
  private Map<String, ArrayList<String>> dictionary;
  private int wordlength;
  private Map<Integer, ArrayList<String>> gameMap;
  private Map<Integer, ArrayList<String>> boardMap;
  ArrayList<Integer> cflList;
  private String gameWord;
  private int level;
  private int [] count;
  
  public TextTwister(String fileName)
  {
    Scanner input = new Scanner(System.in);
    System.out.print("Select Difficulty [1 for easy, 2 for medium 3 for hard]: ");
    String difficulty = input.next();
    while (!difficulty.equals("1") && !difficulty.equals("2") && !difficulty.equals("3"))
    {
      System.out.print("Select Difficulty [1 for easy, 2 for medium 3 for hard]: ");
      difficulty = input.next();
    }
    if(difficulty.equals("1"))
      level = 1;
    if(difficulty.equals("2"))
      level = 2;
    if(difficulty.equals("3"))
      level = 3; 
    wordlength = 12;
    
    Map<String, ArrayList<String>> m = new TreeMap<String, ArrayList<String>>();
    Map<String, ArrayList<String>> d = new TreeMap<String, ArrayList<String>>();
    
    Scanner scanner =getFileScanner(fileName);
    ArrayList<String> keylist = new ArrayList<String>();
    while (scanner.hasNext())
    {
      String orig_word = scanner.next();
      String sortedWord = sorter(orig_word);
      if(orig_word.length() <= wordlength)
      {
        if (!m.containsKey(sortedWord))
        {
          d.put(sortedWord, new ArrayList<String>());
        }
        d.get(sortedWord).add(orig_word);
      }
      
      if (orig_word.length() >= wordlength)
      {
        if (!m.containsKey(sortedWord))
        {
          m.put(sortedWord, new ArrayList<String>());
          keylist.add(sortedWord);
        }
        m.get(sortedWord).add(orig_word);
      }
    }
    map = m;
    dictionary = d;
    
    int randindex = RNG(0, keylist.size());
    
    String randword = keylist.get(randindex);
    String printWord = "";
    for (int i=0; i<randword.length(); i++)
    {
      printWord = printWord + randword.charAt(i) +" ";
    }
    System.out.println("Make words with: "+ printWord);
    System.out.println("Make 5 words for each word length");
    ArrayList <String> possibleWords = map.get(randword);
    String randyWord = possibleWords.get(0);
    printHelper(randyWord);
    boardMap = new TreeMap<Integer, ArrayList<String>>();
    createBoard();
    playGame();
  }
  
  private void printHelper(String orig_word)
  {
    Map<Integer, ArrayList<String>> al = new TreeMap<Integer, ArrayList<String>>();
    PowerSet ps = new PowerSet(orig_word.length());
    String cf = ""; //canonical form
    cflList = new ArrayList<Integer>();
    int[] a;
    int cfl = 0;  //length
    while (ps.hasNext())
    {
      a = ps.next();
      cf = "";
      for (int i = 0; i < a.length; i++)
      {
        cf = cf + orig_word.charAt(a[i]);
      }
      cf = this.sorter(cf);
      
      if (dictionary.containsKey(cf))
      {
        cfl = cf.length();
        if (!al.containsKey(cfl))
        {
          al.put(cfl, new ArrayList<String>());
          cflList.add(cfl);
        }
        
        ArrayList<String> list1 = dictionary.get(cf);
        for (int l = 0; l<list1.size() && list1!=null; l++)
        {
          if(!al.get(cfl).contains(list1.get(l)))
            al.get(cfl).add(list1.get(l));
        }
      }
    }  
    gameMap = al;
  }
  
  
  public void createBoard()
  {
    ArrayList<Integer> boardList = rules();
    for (int i =0; i < boardList.size(); i++)
    {
      boardMap.put(boardList.get(i), new ArrayList<String>());
    }
  }
  
  public ArrayList<Integer> rules()
  {
    ArrayList<Integer> boardList = new ArrayList<Integer>();
    
    if (level == 1)
    {
      boardList.add(3);
      boardList.add(4);
    }
    if (level == 2)
    {
      boardList.add(3);
      boardList.add(4);
      boardList.add(5);
    }
    if (level == 3)
    {
      boardList.add(3);
      boardList.add(4);
      boardList.add(5);
      boardList.add(6);
      boardList.add(7);
    }
    return boardList;
  }
  
  public void playGame()
  {
    Scanner input = new Scanner(System.in);
    printBoard();
    
    ArrayList<Integer> orig = rules();
    count = new int[orig.size()];
    System.out.print("Guess a word or # to quit");
    gameWord = input.next();
    while (!isGameOver() && !gameWord.equals("#"))
    {
      
      
      int lenWord = gameWord.length();
      if(orig.contains(lenWord))
      {
        if (gameMap.get(lenWord).contains(gameWord) && !boardMap.get(lenWord).contains(gameWord))
        {
          int index=0;
          for(int o = 0; o<orig.size(); o++)
          {
            if(orig.get(o)==lenWord)
              index = o;
          }
          
          if(count[index]<5)
            boardMap.get(lenWord).add(gameWord);
          
          count[index]++;
        }
      }
      printBoard();
      if (!isGameOver())
      {
        System.out.print("Guess a word or # to quit");
        gameWord = input.next();
      }
    }
    if (isGameOver())
      System.out.println("You Win a cookie!!!");
    else
      System.out.println("You Lose and fail at life !!!");
  }
  
  private boolean isGameOver()
  {
    for(int k = 0; k<count.length; k++)
    {
      if (count[k] < 5)
        return false;
    }
    return true;
  }
  
  private void printBoard()
  {
    ArrayList<Integer> boardList =rules();
    for (int i= 0; i<boardList.size(); i++)
    {
      int noOfWords = gameMap.get(boardList.get(i)).size();
      ArrayList<String> temp = boardMap.get(boardList.get(i));
      System.out.println(boardList.get(i)+" letter words ("+noOfWords+" possibilities): "+temp);
    }
  }
  
  private int RNG(int lowerbound, int upperbound)
  {
    int rnd = (int)(Math.random()*(upperbound - lowerbound)) + lowerbound +1;
    return rnd;
  }
  
  public static Scanner getFileScanner(String filename)
  {
    Scanner scanner = null;
    try { scanner = new Scanner(new java.io.File(filename)); }
    catch (Exception e)
    {
      System.out.println("File not found: " + filename);
      return null;
    }
    return scanner;
  }
  
  public String sorter(String s) //sorter
  {
    char[] charArray = s.toCharArray();
    int n = s.length();
    for (int i = 0; i < n; i++)
    {
      int index = i;
      char valueToInsert = charArray[index];
      while (index > 0 && (valueToInsert<(charArray[index-1])))
      {
        charArray[index] = charArray[index-1];
        index--;
      }
      charArray[index] = valueToInsert;  
    }
    String sortedWord = String.valueOf(charArray);
    return sortedWord;
  }  
  
  public static void main (String [] args)
  {
    TextTwister a = new TextTwister("words.txt"); 
  }
}