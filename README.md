# Inditex challenge

## Some notes

- I have changed the date format in to the test cases http00.json and http01.json to match the format of the dates. 
As I understand, the output date format has to be as `"yyyy-MM-dd'T'HH:mm:ss'Z'"` at least it's what I'm watching here:

![challenge2.jpg](_doc%2F_img%2Fchallenge2.jpg)
 

## Friendly reminder to run the app

Run

```bash
mvn clean spring-boot:run
```

Test

```bash
mvn clean test; cat target/customReports/result.txt
```

Install

```bash
mvn clean install
```

## coverage

![coverage.jpg](_doc%2F_img%2Fcoverage.jpg)