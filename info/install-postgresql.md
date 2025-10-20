# PostgreSQL Installation Guide



---

## 🍎 macOS (Homebrew)

### Step 1: Install Homebrew (if not installed)
```bash
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
````

### Step 2: Update Homebrew

```bash
brew update
```

### Step 3: Install PostgreSQL

```bash
brew install postgresql
```

### Step 4: Start PostgreSQL Service

```bash
brew services start postgresql
```

### Step 5: Verify Installation

```bash
psql --version
```

### Step 6: Access PostgreSQL Shell

```bash
psql postgres
```

### Step 7: Stop PostgreSQL Service

```bash
brew services stop postgresql
```

---

## 🪟 Windows

### Step 1: Download Installer

Go to the official PostgreSQL download page:
👉 [https://www.postgresql.org/download/windows/](https://www.postgresql.org/download/windows/)

### Step 2: Run the Installer

* Select components (keep default)
* Choose the installation directory
* Set a **password** for the PostgreSQL superuser (`postgres`)
* Note the **port number** (default: `5432`)

### Step 3: Finish Setup

Once installation completes, PostgreSQL will start automatically as a Windows service.

### Step 4: Verify Installation

Open **Command Prompt** or **PowerShell** and run:

```bash
psql --version
```

### Step 5: Access PostgreSQL Shell

Open **SQL Shell (psql)** from the Start Menu or run:

```bash
psql -U postgres
```

---

## 🐧 Linux (Ubuntu)

### Step 1: Update Package List

```bash
sudo apt update
```

### Step 2: Install PostgreSQL and contrib packages

```bash
sudo apt install postgresql postgresql-contrib -y
```

### Step 3: Check PostgreSQL Service Status

```bash
sudo systemctl status postgresql
```

To start (if not running):

```bash
sudo systemctl start postgresql
```

Enable at startup:

```bash
sudo systemctl enable postgresql
```

### Step 4: Switch to PostgreSQL User

```bash
sudo -i -u postgres
```

### Step 5: Access PostgreSQL Shell

```bash
psql
```

### Step 6: Exit Shell

```sql
\q
```

---

## ✅ Verification

Run the following command to confirm installation and version:

```bash
psql --version
```

You should see output like:

```
psql (PostgreSQL) 16.x
```

---

## ⚙️ Useful Commands

| Command         | Description            |
|-----------------|------------------------|
| `psql`          | Start PostgreSQL shell |
| `\l`            | List all databases     |
| `\c dbname`     | Connect to a database  |
| `\dt`           | List tables            |
| `\q`            | Quit shell             |
| `createdb mydb` | Create a new database  |
| `dropdb mydb`   | Delete a database      |

---

## 🧹 Uninstallation

### macOS

```bash
brew uninstall postgresql
```

### Windows

Use **Control Panel → Programs and Features → Uninstall PostgreSQL**.

### Ubuntu

```bash
sudo apt remove postgresql* --purge -y
sudo apt autoremove -y
sudo rm -rf /var/lib/postgresql/
sudo rm -rf /etc/postgresql/
```

---

