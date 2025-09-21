# Cookie Finder

A command-line tool to find the most active cookies from log files for a specific date.

## What it does

Given a CSV file with cookie logs and a date, this tool finds which cookie(s) appeared most frequently on that day. Handles ties properly - if multiple cookies have the same max count, it returns all of them.

## Quick start

```bash
# Clone the repository
git clone https://github.com/muhammadmohsinnisar/cookieFinder.git
cd cookieFinder

# Build the project
./gradlew build

# Run with sample data
./gradlew run --args="-f src/main/resources/cookie_log_test.csv -d 2018-12-09"
# Output: AtY0laUfhglK3lC7

# Or use the JAR directly
java -jar build/libs/cookieFinder-1.0-SNAPSHOT.jar -f cookie_log.csv -d 2018-12-09
```

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

The CSV file should have this format:
```csv
cookie,timestamp
AtY0laUfhglK3lC7,2018-12-09T14:19:00+00:00
SAZuXPGUrfbcn5UA,2018-12-09T10:13:00+00:00
5UAVanZf6UtGyKVS,2018-12-09T07:25:00+00:00
```

Timestamps are in ISO 8601 format with timezone info. The tool converts everything to UTC for date comparison.

## Output

Prints the most active cookie name(s) to stdout, one per line. If multiple cookies tie for the highest count, all are printed.

## Building from source

```bash
# Clone the repo
git clone <your-repo-url>
cd cookieFinder

# Build
./gradlew build

# Run tests
./gradlew test
```

## Requirements

- Java 17 or higher
- Gradle (or use the included wrapper)

## How it works

1. Parses command line arguments (`-f` and `-d`)
2. Reads the CSV file
3. Filters entries by the specified date (converts to UTC)
4. Counts occurrences of each cookie
5. Finds cookies with the maximum count
6. Outputs results to stdout

## Error handling

- Missing arguments: Shows usage message and exits
- File not found: Clear error message
- Invalid date format: Explains the expected format
- Empty results: Prints "No cookies found for date X"

## Testing

The project includes comprehensive tests:
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

## License

MIT License - feel free to use this code however you want.
