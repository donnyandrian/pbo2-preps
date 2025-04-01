# Persiapan Pertemuan 4

## Detail Pengerjaan

### Buat Class `Product` sesuai dengan kolom yang ada pada dataset!
- Column: [ `InvoiceNo`, `StockCode`, `Description`, `Quantity`, `InvoiceDate`, `UnitPrice`, `CustomerID`, `Country` ]
- Column Data Type: [ `Integer`, `String`, `String`, `Integer`, `LocalDateTime`, `Double`, `Integer`, `String` ]
- Example Data:
    | **InvoiceNo** | **StockCode** | **Description** | **Quantity** | **InvoiceDate** | **UnitPrice** | **CustomerID** | **Country** |
    | --- | --- | --- | --- | --- | --- | --- | --- |
    | 536365 | 85123A | WHITE HANGING HEART T-LIGHT HOLDER | 6 | 12/1/2010  08:26:00 AM | 2.55 | 17850 | United Kingdom |

### Gunakan Collections API (list, set, dan map) untuk mengelola dan menganalisis dataset tersebut, dengan ketentuan sbb:
- `List<Product>` untuk menyimpan daftar produk ke dalam bentuk list
- `Set<String>` untuk mendapatkan daftar unik negara pelanggan
- `Map<String, Integer>` untuk menghitung total produk yang terjual berdasarkan `StockCode`
- `Map<String, Double>` untuk menghitung total pendapatan per negara
- `Map<String, Product>` untuk mencari produk berdasarkan `StockCode`

### Push tugas ini ke repository github Anda!