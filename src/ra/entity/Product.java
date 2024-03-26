package ra.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Product {
    private String productId;
    private String productName;
    private float price;
    private String description;
    private Date created;
    private int catalogId;
    private int productStatus = 0;

    public Product() {
    }

    public Product(String productId, String productName, float price, String description, Date created, int catalogId, int productStatus) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.created = created;
        this.catalogId = catalogId;
        this.productStatus = productStatus;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public int getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(int productStatus) {
        this.productStatus = productStatus;
    }

    public void inputData(Scanner scanner, Product[] arrProduct, Categories[] arrCategories, boolean isAdd) {
// Nhập mã sản phẩm đồ uống, gồm 4 ký tự bắt
//đầu là một trong 3 ký tự (C: các đồ uống là café, S: các đồ
//uống là sinh tố, A: các đồ ăn nhanh), không được trùng lặp
        if (isAdd) {
            System.out.println("Nhập vào mã sản phẩm");
            while (true) {
//            nhập id
                this.productId = scanner.nextLine();
//            xét điều kiện không được trùng lặp và đúng định dạng
                if (productId.matches("^[C|S|A][0-9]{3}$") && existProductID(arrProduct)) {
                    break;
                } else {
                    System.err.println("Vui lòng nhập lại mã sản phẩm");
                }
            }
        }
//productName – String: tên sản phẩm đồ uống, có từ 10-50 ký tự, không được trùng lặp
        System.out.println("Nhập vào tên sản phẩm:");
        while (true) {
            this.productName = scanner.nextLine();
            if (this.productName.length() >= 10 && this.productName.length() <= 50 && existProductName(arrProduct)) {
                break;
            } else {
                System.out.println("Vui lòng nhập lại tên sản phẩm");
            }
        }

//• price – float: giá sản phẩm có giá trị lớn hơn 0
        System.out.println("Nhập vào giá sản phẩm");
        while (true) {
            this.price = Float.parseFloat(scanner.nextLine());
            if (price > 0) {
                break;
            } else {
                System.err.println("Vui lòng nhập lại giá sản phẩm");
            }
        }
//• description – String: mô tả sản phẩm
        System.out.println("Nhập mô tả sản phẩm");
        while (true) {
            this.description = scanner.nextLine();
            if (this.description.trim().isEmpty()) {
                System.out.println("Vui lòng nhập mô tả sản phẩm");
            } else {
                break;
            }
        }
//• created – date: ngày nhập sản phẩm có định dạng dd/mm/yyyy
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        while (true) {
            try {
                this.created = sdf.parse(scanner.nextLine());
                break;
            } catch (ParseException e) {
                System.err.println("Vui lòng nhập lại ngày nhập sản phẩm");
            }
        }
//• catalogId – int: Mã danh mục mà sản phẩm thuộc về
        System.out.println("=========================================");
        for (Categories c : arrCategories) {
            c.displayData();
        }
        System.out.println("=========================================");
        System.out.println("Nhập vào catalogID");
        while (true) {
            this.catalogId = Integer.parseInt(scanner.nextLine());
            if (existCatalogID(arrCategories)) {
                break;
            } else {
                System.out.println("Vui lòng nhập lại");
            }
        }
//• productStatus – int: trạng thái sản phẩm, chỉ nhận 1 trong
//các trạng thái sau (0: Đang bán – 1: Hết hàng – 2: Không bán)
    }

    //    phuơng thức kiểm tra trùng lặp CatalogID
    private boolean existCatalogID(Categories[] categories) {
        for (int i = 0; i < categories.length; i++) {
            if (categories[i] != null && categories[i].getCatalogId() == this.catalogId) {
                return true;
            }
        }
        return false;
    }

    //    phuơng thức kiểm tra trùng lặp tên
    public boolean existProductName(Product[] product) {
        for (int i = 0; i < product.length; i++) {
            if (product[i] != null && product[i].getProductName().equals(this.getProductName())) {
                return true;
            }
        }
        return false;
    }
    //    phuơng thức kiểm tra trùng lặp id

    public boolean existProductID(Product[] product) {
        for (int i = 0; i < product.length; i++) {
            if (product[i] != null && product[i].getProductId().equals(this.getProductId())) {
                return true;
            }
        }
        return false;
    }

    public void displayData() {
        String status = null;
        switch (this.productStatus) {
            case 0:
                status = "Đang bán";
                break;
            case 1:
                status = "Hết hàng";
                break;
            case 2:
                status = "Không bán";
                break;
        }
        System.out.printf("| ID : %-2d | ProductName : %s | Price : %s | Description : %s | Creatd : %s | CatalogId : %d | ProductStatus : %s |\n"
                , this.productId, this.productName, this.price, this.description, this.created.toString(), this.catalogId, status);
    }
}
