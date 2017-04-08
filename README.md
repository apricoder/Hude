# Huffmann Ukrainian Decoder Encoder

Main purpose of this application is to encode and decode ukrainian language text ~~almost~~ without losing it's quality.

Currently solution consists of 3 modules: *counter*, *coder* and *common*.

**Counter** - the starting module - was created to analyze frequency of each ukrainian language symbol 
and related common symbols by counting their occurrences in a list of 
[26 most popular ukrainian literature masterpieces](https://github.com/olebokolo/Hude/tree/master/counter/src/test/resources/literature). 
To reproduce such experiment un-ignore appropriate test in counter 
[ApplicationSpek](https://github.com/olebokolo/Hude/blob/master/counter/src/test/kotlin/com/kolodiychuk/alex/hude/counter/ApplicationSpek.kt)

**Coder** - module which, based on rating provided by previous module, builds a 
Huffman tree and then uses it to encode or decode given ukrainian language text.

Example of *coding tree* fragment built by 
[TreeBuilder](https://github.com/olebokolo/Hude/blob/master/coder/src/main/kotlin/com/kolodiychuk/alex/hude/coder/core/tree/TreeBuilder.kt)
and described in 
[TreeBuilderSpek](https://github.com/olebokolo/Hude/blob/master/coder/src/test/kotlin/com/kolodiychuk/alex/hude/coder/core/tree/TreeBuilderSpek.kt)

```
/* * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *
 *    Visual representation of bottom-left corner
 *    of tree built from calculated ukrainian symbols
 *    occurrences rating (space replaced with underscore)
 *
 *
 *                      ...                    ...
 *
 *
 *              /                 \
 *         (_оаи)                 (нвіт)
 *         /    \                 /    \
 *        /      \               /      \
 *     (_о)      (аи)         (нв)      (іт)
 *     /  \      /  \         /  \      /  \
 *   (_)  (о)  (а)  (и)     (н)  (в)  (і)  (т)
 *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
 ```
 
Example of coder in action - output of 
[CoderSpek](https://github.com/olebokolo/Hude/blob/master/coder/src/test/kotlin/com/kolodiychuk/alex/hude/coder/core/text/CoderSpek.kt):

```
* * * * * * * * * * * * * * * * * * * *
Text:
Цей текст, доволі довгий, буде закодовано 
використовуючи мапу, приготовану на основі рейтингу 
появлень літер в українській мові. Розкодований 
код повинен дорівнювати оригінальному тексту. Регістр 
літер при цьому ігнорується.


Encoded:
10000100100001101000000000011100100000110000100100
01110100000000000011100000010001010000010010100001
10000000001110000001000101010110000011011010010000
00000001010100110100111000100000000001001100001000
11000000010011100000010001010000100001000000010000
00011000000101000011001100000001001011000011001001
00011100000100010100110101111001100100001100000000
11110000100100010011010100000000000100010010110000
11010110000001000111000001000101000010000100001101
00000000010000001000000000000100100100010000000100
01010001100000000010110010000110100001110000110001
00010110001101000000011000010001000001010010000101
00101000100000010001011100000000101000011000011100
10000010110000000001010000000011010011000010110000
10100000000100001001010111001100000110011010000000
00111100000100010100011001010000000000101100000101
00110011000000010011100000010001010000100001000000
11011010000000011000001100000001001110000000010001
00000100010100001100010000100000010000000000111000
00010010110001100001010001000111100001010000100001
11000011000000000001001011000011010110000110000100
00001000101001011100010000000100111100110100000000
01110010000011000010010001110011010101000000000010
11001000010110000110001001000111001011000000011000
00101000011000011100100000101100000001000100101100
00110000001000010101110000010011110011010000000001
10010110000100000001001011001101100010000111010111
001001010010010100



Decoded:
цей текст, доволі довгий, буде закодовано 
використовуючи мапу, приготовану на основі рейтингу 
появлень літер в українській мові. розкодований 
код повинен дорівнювати оригінальному тексту. регістр 
літер при цьому ігнорується.


* * * * * * * * * * * * * * * * * * * *
```
**Common** - module that supports other ones by providing utilities, for example 
[flattening](https://github.com/olebokolo/Hude/blob/master/common/src/main/kotlin/com/kolodiychuk/alex/hude/common/Flattening.kt)
lists or arrays which wasn't available until Kotlin 1.1

### To do

* Decode / encode using *bits* (currently it is done on string representations of bits, like on snippet above)
* Provide **coder** command line interface to be able to specify *input files* and *operation* to do on them: *encode* or *decode*
* **Check if tree can be optimized** (currently that's *balanced tree* but may be that more frequent symbols should be placed closer to the root of tree in cost of putting seldom ones further)



