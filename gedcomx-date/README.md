# GEDCOM X Date
This is a spec-compliant implementation of [Gedcom X Dates](https://github.com/FamilySearch/gedcomx/blob/master/specifications/date-format-specification.md) with one exception: 5.3.2 - non-normalized durations.
It also includes some handy utility functions for manupulating ranges, durations, and recurring dates.

# Coordinates

| groupId       | artifactId     |
|---------------|----------------|
| `org.gedcomx` | `gedcomx-date` |

See [the section on using these libraries](../README.md#Use).

To determine the latest version, [read the Maven Metadata](https://repository-gedcom.forge.cloudbees.com/release/org/gedcomx/gedcomx-model/maven-metadata.xml)
and use the "release" version.

# Exceptions
Every error thrown is an instance of `GedcomxRuntimeException`, which is a *runtime* exception.
Gedcomx Date was designed to give you and/or the end user as much information as possible.
For instance, when parsing fails, the message in the exception will tell you exactly what failed and where.

# Date Types
Each date extends the abstract class `GedcomxDate`.

````java
GedcomxDate date = GedcomxDateUtil.parse("A+1900");

date.getType();
// returns GedcomxDateType.APPROXIMATE
// Very useful for case statements ;)

date.isApproximate();
// returns true

date.toFormalString();
// returns A+1900

````

**Simple**

The most basic of dates.
For example, `+1000`, `+2013-01-30`, `-0500-12-31T15:30:49`, and `+1987-03-25T01:00:00-06:00` are all examples of simple dates.

````java
GedcomxDateSimple simple = new GedcomxDateSimple("+1000");

simple.getYear();
// returns 1000

simple.getMonth();
// returns null

simple.isApproximate();
// returns false

simple.toFormalString();
// returns +1000
````

**Approximate**

A Simple Date prepended with `A`.
For example, `A+1835` means approximately 1835.

````java
GedcomxDateSimple simple = new GedcomxDateSimple("+1000");

simple.getYear();
// returns 1000

simple.getMonth();
// returns null

simple.isApproximate();
// returns false

simple.toFormalString();
// returns +1000
````

**Range**

A Date range comprised of two simple dates or a simple date and a duration.
For example, `+1900/1910` means between 1900 to 1910.
Alternatively, `+1970-01-01T24:00:00/P3Y2D` means between midnight on January 1, 1970 and midnight on January 3, 1973.
A range may also be approximate (`A+2013-01/2014-03`), or only have one date (`/+1000` means up to the year 1000 and `+1970-01/` means starting January 1970).

````java
GedcomxDateRange range = new GedcomxDateRange("+1900/1910");

range.toFormalString();
// returns +1900/1910

GedcomxDateSimple start = range.getStart();
start.toFormalDate();
// returns +1900

GedcomxDateSimple end = range.getEnd();
end.toFormalDate();
// returns +1910

GedcomxDateDuration duration = range.getDuration();
duration.toFormalDate();
// returns P10Y
````

**Recurring**

A Recurring date, specified by an optional number of times.
For example, `R3/+1900/P1Y` means this date starts in 1900, and recurs 2 times, ending in 1903.
`R/-500/P1Y5D`, `R500/+1000-01-01/+1001-03-15`, and `R/+1970-01-01T24:00:00/P3Y2D` are also recurring dates.

````java
GedcomxDateRecurring recurring = new GedcomxDateRecurring("R3/+1900/P1Y");

recurring.toFormalString();
// returns R3/+1900/P1Y

GedcomxDateRange range = recurring.getRange();
range.toFormalString();
// returns +1900/P1Y

GedcomxDateSimple start = recurring.getStart();
start.toFormalDate();
// returns +1900

GedcomxDateSimple end = recurring.getEnd();
end.toFormalDate();
// returns +1903

GedcomxDateDuration duration = recurring.getDuration();
duration.toFormalDate();
// returns P1Y

GedcomxDateSimple nth = recurring.getNth(15);
nth.toFormalDate();
// returns +1915
````

**Duration**

A special sub-date of sorts, a duration represents the amount of time that has passed from a given starting date.
You can get the duration from a range or recurring

````java
GedcomxDateDuration duration = new GedcomxDateRecurring("P1Y35D");

duration.getYears();
// returns 1

duration.getMonths();
// returns null

duration.getDays();
// returns 35

duration.toFormalString();
// returns P1Y35D
````

# Utility functions
All of these functions are static under the `GedcomxDateUtil` class

**parse(date)**
Parse a formal date string into the appropriate date type

````java
GedcomxDate date = GedcomxDateUtil.parse("A+1900");
// date is an instance of GedcomxDateApproximate

GedcomxDate date = GedcomxDateUtil.parse("R3/+1900/P1Y");
// date is an instance of GedcomxDateRecurring

GedcomxDate date = GedcomxDateUtil.parse("Bogus");
// throws an instance of GedcomxDateException
````

**getDuration(startDate, endDate)**
Get the duration between two simple dates. Throws an exception if start >= end.

````java
GedcomxDateDuration duration = GedcomxDateUtil.getDuration(new GedcomxDateSimple("+1000"),new GedcomxDateSimple("+1100"));
// returns a duration of P100Y
````

**addDuration(startDate, duration)**
Add a duration to a simple starting date and returns the resulting SimpleDate.

````java
GedcomxDateSimple date = GedcomxDateUtil.addDuration(new GedcomxDateSimple("+1000"),new GedcomxDateDuration("P1Y3D"));
// returns a date of +1001-01-04
````


**multiplyDuration(duration, multiplier)**
Multiply a duration by an integer value

````java
GedcomxDateduration duration = GedcomxDateUtil.multiplyDuration(new GedcomxDateDuration("P1Y3D"),4);
// returns a date of P4Y12D
````

**daysInMonth(month, year)**
Returns the number of days in the given month accounting for the year (leapyear or not).

````java
int days;
days = GedcomxDateUtil.daysInMonth(2, 2003);
// returns 28, non leap year
days = GedcomxDateUtil.daysInMonth(2, 2004);
// returns 29, leap year
days = GedcomxDateUtil.daysInMonth(2, 1900);
// returns 28, non leap year
days = GedcomxDateUtil.daysInMonth(2, 2000);
// returns 29, leap year
````