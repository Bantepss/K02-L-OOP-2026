# Nangor Blast - otomatisasi kompilasi, build, menjalankan, dan membersihkan program.
# Kebutuhan: JDK 21 dan GNU Make. Kode sumber berada di folder src (package nangorblast).

SRC_DIR   := src
BUILD_DIR := build
MAIN      := nangorblast.Main
SOURCES   := $(shell find $(SRC_DIR) -name '*.java' 2>/dev/null)

.PHONY: all build run clean

all: build

build:
ifeq ($(strip $(SOURCES)),)
	@echo "Belum ada source code di $(SRC_DIR)/."
else
	@mkdir -p $(BUILD_DIR)
	javac -d $(BUILD_DIR) $(SOURCES)
endif

run: build
ifeq ($(strip $(SOURCES)),)
	@echo "Belum ada program yang dapat dijalankan."
else
	java -cp $(BUILD_DIR) $(MAIN)
endif

clean:
	rm -rf $(BUILD_DIR)
