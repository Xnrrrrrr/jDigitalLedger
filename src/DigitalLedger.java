import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// POJO class for Credit Card Transactions
class CreditCardTransaction {
    private String cardNumber;
    private Date transactionDate;
    private double amount;
    private String merchant;

    // CreditCard Transaction constructor method
    public CreditCardTransaction(String cardNumber, Date transactionDate, double amount, String merchant) {
        this.cardNumber = cardNumber;
        this.transactionDate = transactionDate;
        this.amount = amount;
        this.merchant = merchant;
    }
    // getters and setters for CreditCardTransaction
    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    // to string method creates a readable string for the CreditCardTransaction object
    @Override
    public String toString() {
        return "CreditCardTransaction{" +
                "cardNumber='" + cardNumber + '\'' +
                ", transactionDate=" + transactionDate +
                ", amount=" + amount +
                ", merchant='" + merchant + '\'' +
                '}';
    }
}

// POJO class for Student Grades
class StudentGrade {
    private String studentId;
    private String courseCode;
    private int grade;

    // StudentGrade constructor method
    public StudentGrade(String studentId, String courseCode, int grade) {
        this.studentId = studentId;
        this.courseCode = courseCode;
        this.grade = grade;
    }
    // getters and setters for StudentGrade
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    // toString method creates a readable string for the StudentGrade object
    @Override
    public String toString() {
        return "StudentGrade{" +
                "studentId='" + studentId + '\'' +
                ", courseCode='" + courseCode + '\'' +
                ", grade=" + grade +
                '}';
    }
}

// POJO class for Banking Transactions
class BankingTransaction {
    private String accountNumber;
    private Date transactionDate;
    private double amount;
    private String transactionType;

    // BankingTransaction constructor method
    public BankingTransaction(String accountNumber, Date transactionDate, double amount, String transactionType) {
        this.accountNumber = accountNumber;
        this.transactionDate = transactionDate;
        this.amount = amount;
        this.transactionType = transactionType;
    }

    // getters and setters for BankingTransaction
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    // toString method creates a readable string for BankingTransaction
    @Override   // override the toString method defined in base object, to customize class
    public String toString() {
        return "BankingTransaction{" +
                "accountNumber='" + accountNumber + '\'' +
                ", transactionDate=" + transactionDate +
                ", amount=" + amount +
                ", transactionType='" + transactionType + '\'' +
                '}';
    }
}
    // Block class
class Block {
    private int index;
    private long timestamp;
    private String previousHash;
    private String hash;
    private String data;
    private int difficulty;     // determines complexity in the mining

        // constructor without diffuculty
    public Block(int index, String previousHash, String data) {
        this.index = index;
        this.timestamp = new Date().getTime();
        this.previousHash = previousHash;
        this.data = data;
        this.hash = mineBlock();
    }
    // constructor with diffuculty ; two allows for flexibility ,
    public Block(int index, String previousHash, String data, int difficulty) {
        this.index = index;
        this.timestamp = new Date().getTime();
        this.previousHash = previousHash;
        this.data = data;
        this.difficulty = difficulty;
        this.hash = mineBlock();
    }
    // getters and setters for block
    public String getPreviousHash() {
        return previousHash;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getIndex() {
        return index;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    // block miner
    public String mineBlock() {
        String target = "0".repeat(difficulty);     // CAN ADD STARTING PREFIX TO CHECK HASH, CONSIDER THIS

        while (true) {
            timestamp = new Date().getTime();
            hash = calculateHash();
            if (hash.substring(0, difficulty).equals(target)) {
                return hash;
            }
        }
    }

    // Computes the hash value of a block object, calculates the sha 256 hash from concatenated strings from Block
    private String calculateHash() {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            String input = index + timestamp + previousHash + data;
            byte[] hashBytes = digest.digest(input.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();

            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException | java.io.UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}

// represents blockchain in a list
class Blockchain {
    private List<Block> chain;

    // constructor of the blockchain class
    public Blockchain() {
        chain = new ArrayList<>();
        chain.add(createGenesisBlock());
    }

    // method resposnible for creating the genesis block, returns block object, index of 0
    public Block createGenesisBlock() {
        return new Block(0, "0", "Genesis Block", 3);
    }

    // method for adding a new block to the blockchain, takes string, retrieves previous block , creates new block, creates new block, increments index
    public void addBlock(String data) {
        Block previousBlock = chain.get(chain.size() - 1);
        Block newBlock = new Block(previousBlock.getIndex() + 1, previousBlock.getHash(), data, 3);
        chain.add(newBlock);
    }

    // method resposible for printing the contents of blockchain to console, iterates through list and prints details for each
    public void printBlockchain() {
        for (Block block : chain) {
            System.out.println("Block #" + block.getIndex());
            System.out.println("Timestamp: " + block.getTimestamp());
            System.out.println("Previous Hash: " + block.getPreviousHash());
            System.out.println("Hash: " + block.getHash());
            System.out.println("Data: " + block.getData());
            System.out.println();
        }
    }
}

// entry point, DigitalLedger class,
public class DigitalLedger {
    public static void main(String[] args) {
        // Create a new blockchain
        Blockchain blockchain = new Blockchain();

        // Add some blocks to the blockchain with POJO data
        CreditCardTransaction creditCardTransaction = new CreditCardTransaction("1234-5678-9012-3456", new Date(), 50.0, "Online Store");
        StudentGrade studentGrade = new StudentGrade("S123456", "Math", 90);
        BankingTransaction bankingTransaction = new BankingTransaction("123456789", new Date(), 1000.0, "Deposit");

        blockchain.addBlock(creditCardTransaction.toString());
        blockchain.addBlock(studentGrade.toString());
        blockchain.addBlock(bankingTransaction.toString());

        // Print the blockchain
        blockchain.printBlockchain();
    }
}

//







