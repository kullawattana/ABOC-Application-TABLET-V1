# Generated by CMake 3.3.2

if("${CMAKE_MAJOR_VERSION}.${CMAKE_MINOR_VERSION}" LESS 2.5)
   chatMessage(FATAL_ERROR "CMake >= 2.6.0 required")
endif()
cmake_policy(PUSH)
cmake_policy(VERSION 2.6)
#----------------------------------------------------------------
# Generated CMake target import file.
#----------------------------------------------------------------

# Commands may need to know the format version.
set(CMAKE_IMPORT_FILE_VERSION 1)

# Protect against multiple inclusion, which would fail when already imported targets are added once more.
set(_targetsDefined)
set(_targetsNotDefined)
set(_expectedTargets)
foreach(_expectedTarget libtiff libjpeg libwebp libjasper libpng IlmImf tbb opencv_core opencv_flann opencv_imgproc opencv_ml opencv_photo opencv_video opencv_imgcodecs opencv_shape opencv_videoio opencv_highgui opencv_objdetect opencv_superres opencv_features2d opencv_calib3d opencv_java opencv_stitching opencv_videostab)
  list(APPEND _expectedTargets ${_expectedTarget})
  if(NOT TARGET ${_expectedTarget})
    list(APPEND _targetsNotDefined ${_expectedTarget})
  endif()
  if(TARGET ${_expectedTarget})
    list(APPEND _targetsDefined ${_expectedTarget})
  endif()
endforeach()
if("${_targetsDefined}" STREQUAL "${_expectedTargets}")
  set(CMAKE_IMPORT_FILE_VERSION)
  cmake_policy(POP)
  return()
endif()
if(NOT "${_targetsDefined}" STREQUAL "")
  chatMessage(FATAL_ERROR "Some (but not all) targets in this export set were already defined.\nTargets Defined: ${_targetsDefined}\nTargets not yet defined: ${_targetsNotDefined}\n")
endif()
unset(_targetsDefined)
unset(_targetsNotDefined)
unset(_expectedTargets)


# Compute the installation prefix relative to this file.
get_filename_component(_IMPORT_PREFIX "${CMAKE_CURRENT_LIST_FILE}" PATH)
get_filename_component(_IMPORT_PREFIX "${_IMPORT_PREFIX}" PATH)
get_filename_component(_IMPORT_PREFIX "${_IMPORT_PREFIX}" PATH)
get_filename_component(_IMPORT_PREFIX "${_IMPORT_PREFIX}" PATH)

# Create imported target libtiff
add_library(libtiff STATIC IMPORTED)

# Create imported target libjpeg
add_library(libjpeg STATIC IMPORTED)

# Create imported target libwebp
add_library(libwebp STATIC IMPORTED)

# Create imported target libjasper
add_library(libjasper STATIC IMPORTED)

# Create imported target libpng
add_library(libpng STATIC IMPORTED)

# Create imported target IlmImf
add_library(IlmImf STATIC IMPORTED)

# Create imported target tbb
add_library(tbb STATIC IMPORTED)

# Create imported target opencv_core
add_library(opencv_core STATIC IMPORTED)

# Create imported target opencv_flann
add_library(opencv_flann STATIC IMPORTED)

# Create imported target opencv_imgproc
add_library(opencv_imgproc STATIC IMPORTED)

# Create imported target opencv_ml
add_library(opencv_ml STATIC IMPORTED)

# Create imported target opencv_photo
add_library(opencv_photo STATIC IMPORTED)

# Create imported target opencv_video
add_library(opencv_video STATIC IMPORTED)

# Create imported target opencv_imgcodecs
add_library(opencv_imgcodecs STATIC IMPORTED)

# Create imported target opencv_shape
add_library(opencv_shape STATIC IMPORTED)

# Create imported target opencv_videoio
add_library(opencv_videoio STATIC IMPORTED)

# Create imported target opencv_highgui
add_library(opencv_highgui STATIC IMPORTED)

# Create imported target opencv_objdetect
add_library(opencv_objdetect STATIC IMPORTED)

# Create imported target opencv_superres
add_library(opencv_superres STATIC IMPORTED)

# Create imported target opencv_features2d
add_library(opencv_features2d STATIC IMPORTED)

# Create imported target opencv_calib3d
add_library(opencv_calib3d STATIC IMPORTED)

# Create imported target opencv_java
add_library(opencv_java SHARED IMPORTED)

# Create imported target opencv_stitching
add_library(opencv_stitching STATIC IMPORTED)

# Create imported target opencv_videostab
add_library(opencv_videostab STATIC IMPORTED)

# Load information for each installed configuration.
get_filename_component(_DIR "${CMAKE_CURRENT_LIST_FILE}" PATH)
file(GLOB CONFIG_FILES "${_DIR}/OpenCVModules_mips-*.cmake")
foreach(f ${CONFIG_FILES})
  include(${f})
endforeach()

# Cleanup temporary variables.
set(_IMPORT_PREFIX)

# Loop over all imported files and verify that they actually exist
foreach(target ${_IMPORT_CHECK_TARGETS} )
  foreach(file ${_IMPORT_CHECK_FILES_FOR_${target}} )
    if(NOT EXISTS "${file}" )
      chatMessage(FATAL_ERROR "The imported target \"${target}\" references the file
   \"${file}\"
but this file does not exist.  Possible reasons include:
* The file was deleted, renamed, or moved to another location.
* An install or uninstall procedure did not complete successfully.
* The installation package was faulty and contained
   \"${CMAKE_CURRENT_LIST_FILE}\"
but not all the files it references.
")
    endif()
  endforeach()
  unset(_IMPORT_CHECK_FILES_FOR_${target})
endforeach()
unset(_IMPORT_CHECK_TARGETS)

# This file does not depend on other imported targets which have
# been exported from the same project but in a separate export set.

# Commands beyond this point should not need to know the version.
set(CMAKE_IMPORT_FILE_VERSION)
cmake_policy(POP)
