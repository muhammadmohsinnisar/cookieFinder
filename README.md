# Cookie Finder

A simple command-line tool that finds the most active cookies from log files for a specific date. Built this as part of a coding challenge, and it turned out pretty useful for analyzing cookie usage patterns.

## What it does

You give it a CSV file with cookie logs and a date, and it tells you which cookie(s) appeared most frequently on that day. Handles ties properly - if multiple cookies have the same max count, you get all of them.

## Installation

### Option 1: Homebrew (macOS)

If you're on macOS, this is the easiest way:

```bash
brew tap muhammadmohsinnisar/tap
brew install cookie-finder
```

### Option 2: Build from source (Linux/macOS/Windows)

For Linux users or if you prefer building from source:

```bash
# Clone the repository
git clone https://github.com/muhammadmohsinnisar/cookieFinder.git
cd cookieFinder

# Build the project (requires Java 17+)
./gradlew build

# Create executable JAR
./gradlew jar
```

Then you can run it with:
```bash
java -jar build/libs/cookieFinder-1.0-SNAPSHOT.jar -f cookie_log.csv -d 2018-12-09
```

### Option 3: Direct download (Linux/Windows)

If you don't want to deal with building, you can download the JAR directly from the [releases page](https://github.com/muhammadmohsinnisar/cookieFinder/releases) and run it with Java.

## Usage

```bash
cookieFinder -f <filename> -d <date>
```

**Parameters:**
- `-f` : Path to the CSV file containing cookie logs
- `-d` : Date in YYYY-MM-DD format (UTC timezone)

**Example:**
```bash
cookieFinder -f cookie_log.csv -d 2018-12-09
```

## Input format

The CSV file should look like this:
```csv
cookie,timestamp
AtY0laUfhglK3lC7,2018-12-09T14:19:00+00:00
SAZuXPGUrfbcn5UA,2018-12-09T10:13:00+00:00
5UAVanZf6UtGyKVS,2018-12-09T07:25:00+00:00
```

Timestamps are in ISO 8601 format with timezone info. The tool converts everything to UTC for date comparison, so you don't have to worry about timezone differences.

## Output

Prints the most active cookie name(s) to stdout, one per line. If multiple cookies tie for the highest count, all are printed.

**Example output:**
```bash
$ cookieFinder -f cookie_log.csv -d 2018-12-09
AtY0laUfhglK3lC7

$ cookieFinder -f cookie_log.csv -d 2018-12-08
SAZuXPGUrfbcn5UA
4sMM2LxV07bPJzwf
fbcn5UAVanZf6UtG
```

## Quick test

Want to see it in action? Try it with the included sample data:

```bash
# If installed via Homebrew
cookieFinder -f src/main/resources/cookie_log_test.csv -d 2018-12-09

# If building from source
./gradlew run --args="-f src/main/resources/cookie_log_test.csv -d 2018-12-09"
```

## Requirements

- Java 17 or higher
- That's it! No other dependencies

## How it works

Pretty straightforward:
1. Parses your command line arguments (`-f` and `-d`)
2. Reads the CSV file
3. Filters entries by the specified date (converts to UTC)
4. Counts occurrences of each cookie
5. Finds cookies with the maximum count
6. Outputs results to stdout

## Error handling

The tool handles common issues gracefully:
- Missing arguments: Shows usage message and exits
- File not found: Clear error message
- Invalid date format: Explains the expected format
- Empty results: Prints "No cookies found for date X"

## Testing

I wrote comprehensive tests for this thing:
- Unit tests for each component
- Integration tests with real data
- Edge cases (empty files, ties, invalid input)

Run tests with:
```bash
./gradlew test
```

## Project structure

```
src/
├── main/kotlin/com/mohsin/cookiefinder/
│   ├── Main.kt                           # Entry point
│   ├── model/
│   │   └── CookieEntry.kt                # Data model
│   ├── cli/
│   │   └── CliParser.kt                  # Command line parsing
│   ├── service/
│   │   ├── CookieCountingService.kt      # Core business logic
│   │   └── FileReaderService.kt          # File I/O
│   └── parser/
│       ├── CookieLogParser.kt            # CSV parsing
│       └── Utils.kt                      # Date utilities
└── test/kotlin/com/mohsin/cookiefinder/  # Tests mirror main structure
    ├── cli/
    ├── service/
    └── parser/
```

## Why I built it this way

- **Custom CLI parser**: Instead of using a library, I wanted to show parsing skills
- **Clean architecture**: Each component has a single responsibility
- **Comprehensive testing**: TDD approach with good coverage
- **Production ready**: Proper error handling and user experience

The code is designed to be maintainable and easy to extend. Each service can be tested independently, and the main function orchestrates everything cleanly.

## Contributing

Feel free to open issues or submit pull requests. This was built as a coding challenge, but I'm happy to improve it based on feedback.

## License

MIT License - use it however you want.