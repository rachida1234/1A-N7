# Définition des Variables (à compléter)
CC = c99
CFLAGS = -Wextra -pedantic -c
LDFLAGS = 
EXE = -o

# Règles (à compléter avec des variables automatiques)
all: exemple_file test_file

test_file: file.o test_file.o
	$(CC) $(LDFLAGS) $^ $(EXE) $@

exemple_file: file.o exemple_file.o
	$(CC) $(LDFLAGS) $^ $(EXE) $@

file.o: file.c file.h
	$(CC) $(CFLAGS) $<

exemple_file.o: exemple_file.c
	$(CC) $(CFLAGS) $<
test_file.o: test_file.c
	$(CC) $(CFLAGS) $<

makedepend: 
	makedepend *.c -Y

clean: 
	rm *.o exemple_file test_file

.PHONY: clean all makedepend

