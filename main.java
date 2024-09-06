import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class main {

    private static JTable table;
    private static JScrollPane scrollPane ;

    private static int listControl=0;

    public static void main(String[] args) {

        ArrayList<Task> tasks = new ArrayList<>();


        JFrame frame = new JFrame("To-Do List");
        frame.setSize(1000,700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JPanel panel = new JPanel();
        panel.setBackground(new Color(70, 50, 50));
        frame.add(panel);
        panel.setLayout(null);




        JButton addTask = new JButton("Add Task");
        addTask.setBounds(150, 40, 120, 75);
        addTask.setBackground(new Color(192, 192, 192));
        panel.add(addTask);
        addTask.addActionListener(clickButton -> addTaskFunction(tasks));

        JButton removeTask = new JButton("Remove Task");
        removeTask.setBounds(350, 40, 120, 75);
        removeTask.setBackground(new Color(192, 192, 192));
        panel.add(removeTask);
        removeTask.addActionListener(clickButton -> removeTaskFunction(frame,tasks));

        JButton editTask = new JButton("Edit Task");
        editTask.setBounds(550, 40, 120, 75);
        editTask.setBackground(new Color(192, 192, 192));
        panel.add(editTask);
        editTask.addActionListener(clickButton -> editTaskFunction(tasks));

        JButton listTask = new JButton("List Task");
        listTask.setBounds(750, 40, 120, 75);
        listTask.setBackground(new Color(192, 192, 192));
        panel.add(listTask);
        listTask.addActionListener(clickButton -> listTaskFunction(panel,tasks));


        frame.setVisible(true);
    }





    public static void addTaskFunction(ArrayList<Task> tasks){
        if(scrollPane!=null){
            scrollPane.setVisible(false);
        }

        JFrame addFrame = new JFrame("To-Do List");
        addFrame.setSize(400,250);
        addFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel addPanel = new JPanel();
        addPanel.setLayout(null);
        addPanel.setBackground(new Color(70, 50, 50));
        addFrame.add(addPanel);



        JLabel l1 = new JLabel("Enter a task name");
        l1.setBounds(150,5,150,30);
        l1.setForeground(new Color(0,0,0));
        addPanel.add(l1);

        JTextField taskName = new JTextField();
        taskName.setBackground(new Color(192, 192, 192));
        taskName.setForeground(new Color(0,0,0));
        taskName.setBounds(150,35,100,30);
        addPanel.add(taskName);

        JLabel l2 = new JLabel("Enter a task's description");
        l2.setBounds(135,75,150,30);
        l2.setForeground(new Color(0,0,0));
        addPanel.add(l2);

        JTextArea taskDescription = new JTextArea();
        taskDescription.setBackground(new Color(192, 192, 192));
        taskDescription.setForeground(new Color(0,0,0));
        taskDescription.setBounds(50,105,300,50);
        taskDescription.setLineWrap(true);
        taskDescription.setWrapStyleWord(true);
        addPanel.add(taskDescription);
        addFrame.setVisible(true);

        JButton saveTask = new JButton("Save Task");
        saveTask.setBackground(new Color(192, 192, 192));
        saveTask.setBounds(150,170,100,30);
        addPanel.add(saveTask);
        saveTask.addActionListener(clickButton -> {
            if(taskName.getText().equals("")){
                JOptionPane.showMessageDialog(addFrame,"Error: You must enter a task name.");
            }else{
                if(taskDescription.getText().equals("")){
                    Task newTask = new Task(taskName.getText());
                    tasks.add(newTask);
                    JOptionPane.showMessageDialog(addFrame,"Successful: Your new task added.");
                    addFrame.dispose();

                }else{
                    Task newTask = new Task(taskName.getText(),taskDescription.getText());
                    tasks.add(newTask);
                    JOptionPane.showMessageDialog(addFrame,"Successful: Your new task added.");
                    addFrame.dispose();
                }
            }
        });

    }



    public static void removeTaskFunction(JFrame frame,ArrayList<Task> tasks){

        if(scrollPane!=null & table!=null){
            scrollPane.setVisible(false);
            table.setVisible(false);
        }

        JFrame removeFrame = new JFrame("To-Do List");
        removeFrame.setSize(400,250);
        removeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel removePanel = new JPanel();
        removePanel.setLayout(null);
        removePanel.setBackground(new Color(70, 50, 50));
        removeFrame.add(removePanel);

        JButton removeButton = new JButton("Remove");
        removeButton.setBounds(140,160,100,40);
        removeButton.setBackground(new Color(192, 192, 192));
        removePanel.add(removeButton);



        removeFrame.setVisible(true);


        DefaultTableModel model = new DefaultTableModel(new Object[]{"Name", "Status"},0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable removeTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(removeTable);
        scrollPane.setBounds(20, 20, 345, 130);
        removeTable.setBackground(new Color(192, 192, 192));
        scrollPane.getViewport().setBackground(new Color(192, 192, 192));
        removeTable.getTableHeader().setBackground(new Color(192, 192, 192));
        removeTable.getTableHeader().setReorderingAllowed(false);
        removeTable.setRowHeight(30);
        removePanel.add(scrollPane);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < removeTable.getColumnCount(); i++) {
            removeTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        for(Task task : tasks){
            if(task.isComplete()){
                model.addRow(new Object[]{task.getName(),"Completed ✓"});
            }else{
                model.addRow(new Object[]{task.getName(),"Not Completed ×"});
            }

        }

        removeButton.addActionListener(clickButton -> {

            int[] selectedRows = removeTable.getSelectedRows();
            if(selectedRows.length==0){
                JOptionPane.showMessageDialog(removeFrame,"Error: You did not select any elements to remove.");
            }else{
                for(int i=0;i<selectedRows.length;i++){
                    JOptionPane.showMessageDialog(removeFrame,"Successful: Elements removed.");
                    tasks.remove(selectedRows[i]);
                }
                removeFrame.dispose();
            }
        });




    }

    public static void editTaskFunction(ArrayList<Task> tasks){

        if(scrollPane!=null){
            scrollPane.setVisible(false);
        }


        JFrame addFrame = new JFrame("To-Do List");
        addFrame.setSize(400,250);
        addFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel addPanel = new JPanel();
        addPanel.setLayout(null);
        addPanel.setBackground(new Color(70, 50, 50));
        addFrame.add(addPanel);

        addFrame.setVisible(true);



        DefaultTableModel model = new DefaultTableModel(new Object[]{"Name", "Status"},0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable removeTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(removeTable);
        scrollPane.setBounds(20, 20, 345, 130);
        removeTable.setBackground(new Color(192, 192, 192));
        scrollPane.getViewport().setBackground(new Color(192, 192, 192));
        removeTable.getTableHeader().setBackground(new Color(192, 192, 192));
        removeTable.getTableHeader().setReorderingAllowed(false);
        removeTable.setRowHeight(30);
        removeTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        addPanel.add(scrollPane);


        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < removeTable.getColumnCount(); i++) {
            removeTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        for(Task task : tasks){
            if(task.isComplete()){
                model.addRow(new Object[]{task.getName(),"Completed ✓"});
            }else{
                model.addRow(new Object[]{task.getName(),"Not Completed ×"});
            }

        }


        JButton saveTask = new JButton("Edit Task");
        saveTask.setBackground(new Color(192, 192, 192));
        saveTask.setBounds(150,170,100,30);
        addPanel.add(saveTask);
        saveTask.addActionListener(clickButton -> {

            if(removeTable.getSelectedRow()==-1){
                JOptionPane.showMessageDialog(addFrame,"Error: You did not select any elements to edit.");
            }else{
                int selectedRow = removeTable.getSelectedRow();
                addPanel.remove(scrollPane);
                addPanel.remove(removeTable);
                addPanel.remove(saveTask);
                addPanel.revalidate();
                addPanel.repaint();

                String name = tasks.get(selectedRow).getName();
                String description =tasks.get(selectedRow).getDescription();

                JLabel l1 = new JLabel("Enter a task name");
                l1.setBounds(150,5,150,30);
                l1.setForeground(new Color(0,0,0));
                addPanel.add(l1);

                JTextField taskName = new JTextField();
                taskName.setBackground(new Color(192, 192, 192));
                taskName.setForeground(new Color(0,0,0));
                taskName.setBounds(150,35,100,30);
                taskName.setText(name);
                addPanel.add(taskName);



                JLabel l2 = new JLabel("Enter a task's description");
                l2.setBounds(135,75,150,30);
                l2.setForeground(new Color(0,0,0));
                addPanel.add(l2);

                JTextArea taskDescription = new JTextArea();
                taskDescription.setBackground(new Color(192, 192, 192));
                taskDescription.setForeground(new Color(0,0,0));
                taskDescription.setBounds(50,105,300,50);
                taskDescription.setLineWrap(true);
                taskDescription.setWrapStyleWord(true);
                taskDescription.setText(description);
                addPanel.add(taskDescription);

                JButton removeButton = new JButton("Edit Task");
                removeButton.setBounds(140,160,100,40);
                removeButton.setBackground(new Color(192, 192, 192));
                addPanel.add(removeButton);
                removeButton.addActionListener(clickRemoveButton -> {
                    JOptionPane.showMessageDialog(addFrame,"Successful: Changes implemented.");
                    addFrame.dispose();
                });


                addFrame.setVisible(true);





            }
        });







    }

    public static void listTaskFunction(JPanel panel, ArrayList<Task> tasks){
        DefaultTableModel model = new DefaultTableModel(new Object[]{"Name", "Description", "Status"},0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        if(listControl==0){


            table = new JTable(model);
            scrollPane = new JScrollPane(table);
            scrollPane.setBounds(100, 180, 800, 400);
            table.setBackground(new Color(192, 192, 192));
            scrollPane.getViewport().setBackground(new Color(192, 192, 192));
            table.getTableHeader().setBackground(new Color(192, 192, 192));
            table.getTableHeader().setReorderingAllowed(false);
            table.setRowHeight(30);

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);

            for (int i = 0; i < table.getColumnCount(); i++) {
                table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }

            for(Task task : tasks){
                if(task.isComplete()){
                    model.addRow(new Object[]{task.getName(),task.getDescription(),"Completed ✓"});
                }else{
                    model.addRow(new Object[]{task.getName(),task.getDescription(),"Not Completed ×"});
                }

            }

            panel.add(scrollPane);
            listControl = 1;

        }else{
            scrollPane.setVisible(false);
            table.setVisible(false);
            listControl=0;
        }

    }

}