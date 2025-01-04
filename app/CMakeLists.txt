# We're required to set some minimum version here
cmake_minimum_required(VERSION 3.18.1)

# Declare a new native library
add_library(
        # Set the name of the library
        my-native-code
        # Designate the library as a shared library (.so)
        SHARED
)

# This helper variable points to the location of the native sources
set(NATIVE_SRC_DIR src/main/cpp)

# Add specific source files to the library
target_sources(
        my-native-code
        PRIVATE
        ${NATIVE_SRC_DIR}/jni_facade.cpp
        ${NATIVE_SRC_DIR}/fibonacci/fibonacci.cpp
)

# Look for header files in these directories
target_include_directories(
        my-native-code
        PRIVATE
        ${NATIVE_SRC_DIR}/fibonacci/
)