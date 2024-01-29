import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExpenseTrackerApp {

    // Initialize an array to store expenses
    private static double[] expenses = new double[100]; // Assuming a maximum of 100 expenses

    // Initialize a variable to keep track of the number of expenses entered
    private static int expenseCount = 0;

    // Define the budget limit
    private static final double BUDGET_LIMIT = 1000.00; // Example budget limit of $1000.00

    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("Expense Tracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        // Create components
        JLabel totalLabel = new JLabel("Total Expenses: $0.00");
        JButton addButton = new JButton("Add Expense");

        // Add action listener to the "Add Expense" button
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Prompt user to enter expense amount
                String expenseStr = JOptionPane.showInputDialog("Enter expense amount:");
                try {
                    // Convert user input to double and add to expenses array
                    double expense = Double.parseDouble(expenseStr); // Convert expenseStr to a double
                    if (expense < 0) {
                        // Display error message for negative expense
                        JOptionPane.showMessageDialog(null, "Invalid input. Expense amount cannot be negative.");
                    } else if (calculateTotalExpenses() + expense > BUDGET_LIMIT) {
                        // Display error message for exceeding budget limit
                        JOptionPane.showMessageDialog(null, "Adding this expense would exceed the budget limit of $" + BUDGET_LIMIT);
                    } else {
                        expenses[expenseCount] = expense; // Add entered expense to expenses array
                        expenseCount++; // Increment expense count
                        // Calculate total expenses
                        double totalExpenses = calculateTotalExpenses();
                        // Display updated total expenses to the user
                        totalLabel.setText("Total Expenses: $" + String.format("%.2f", totalExpenses));
                    }
                } catch (NumberFormatException ex) {
                    // Display error message for invalid input
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.");
                }
            }
        });

        // Create a panel to hold components
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(totalLabel);
        panel.add(addButton);

        // Add panel to the frame
        frame.add(panel);

        // Set frame visibility
        frame.setVisible(true);
    }

    // Method to calculate the total expenses from the expenses array
    private static double calculateTotalExpenses() {
        double total = 0;
        for (int i = 0; i < expenseCount; i++) {
            total += expenses[i];
        }
        return total;
    }
}
