import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class App extends JFrame {
    JPanel leftPanel;
    JPanel rightPanel;
    JPanel leftUp;
    JPanel leftDown;
    JLabel info;
    JTextField textfield;
    JButton bubbleSort;
    JButton selectionSort;
    JButton insertionSort;
    JButton clear;
    private JTextArea outpuTextArea;


    App(){
        setTitle("Sorting");
        setVisible(true);
        setMinimumSize(new Dimension(300, 500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new GridLayout(1,1));
        JPanel subPanel = new JPanel(new GridLayout(1,2));
        subPanel.add(leftPanel());
        subPanel.add(rightPanel());
        add(subPanel);
        pack();
        setLocationRelativeTo(null);

    }

    
    private JPanel leftPanel() {
        leftPanel = new JPanel(new GridLayout(2,1));
        leftPanel.add(leftUp());
        leftPanel.add(leftDown());

        return leftPanel;
    }


    private JPanel leftUp(){
        leftUp = new JPanel(new GridBagLayout());
        leftUp.setBorder(BorderFactory.createTitledBorder("Input"));
        info = new JLabel("Enter array seperated by space or ','");
        textfield = new JTextField(20);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(4, 4, 4, 4);

        gbc.gridx = 0;
        gbc.gridy = 0;
        leftUp.add(info, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        leftUp.add(textfield, gbc);

        

        return leftUp;
    }
    
    private JPanel leftDown(){
        leftDown = new JPanel();
        leftDown.setLayout(new GridLayout(3,1));
        leftDown.setBorder(BorderFactory.createTitledBorder("Algorithms"));
        leftDown.add(bubbleSort = new JButton("Bubble Sorting"));
        leftDown.add(selectionSort = new JButton("Selection Sorting"));
        leftDown.add(insertionSort = new JButton("Insertion Sorting"));


        bubbleSort.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Integer[] arra = alist(textfield.getText());
                outpuTextArea.append("Bubble Sorting:\n");
                bubbleSort(arra);
                outpuTextArea.append("\n");
            }
        });
        selectionSort.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Integer[] arra = alist(textfield.getText());
                outpuTextArea.append("Selection Sorting:\n");
                selectionSort(arra);
                outpuTextArea.append("\n");
            }
        });
        insertionSort.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Integer[] arra = alist(textfield.getText());
                outpuTextArea.append("Insertion Sorting:\n");
                insertionSort(arra);
                outpuTextArea.append("\n");
            }
        });


        return leftDown;
    }


    private JPanel rightPanel(){
        rightPanel = new JPanel(new BorderLayout());
        outpuTextArea = new JTextArea();
        outpuTextArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(outpuTextArea);
        scrollPane.setBounds(10,20,200,200);
        rightPanel.add(scrollPane);
        rightPanel.add(clear = new JButton("Clear"), BorderLayout.SOUTH);
        
        rightPanel.setBorder(BorderFactory.createTitledBorder("Output"));

        clear.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                textfield.setText(null);
                outpuTextArea.setText(null);
            }
        });

        return rightPanel;
    }


    public void insertionSort(Integer[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i-1;
    
            while (j >= 0 && arr[j] > key) {
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = key;
            outpuTextArea.append(Arrays.toString(arr) + "\n");  // call printArray method to print the current state of the array
        }
    }

    public void bubbleSort(Integer[] arr) {
        int n = arr.length;
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-i-1; j++) {
                if (arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
            outpuTextArea.append(Arrays.toString(arr) + "\n");  // call printArray method to print the current state of the array
        }
    }

    public void selectionSort(Integer[] arr) {
        int n = arr.length;
        for (int i = 0; i < n-1; i++) {
            int minIndex = i;
            for (int j = i+1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
            outpuTextArea.append(Arrays.toString(arr) + "\n");  // call printArray method to print the current state of the array
        }
    }

    public static void printArray(Integer[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
    
    public Integer[] alist(String input){
        String[] inputArr = input.split("[,\\s]+");
        Integer[] arr = new Integer[inputArr.length];
        for (int i = 0; i < inputArr.length; i++) {
            arr[i] = Integer.parseInt(inputArr[i]);
        }
        return arr;
    }
    
    
    public static void main(String[] args) {
        new App();
    }
}
