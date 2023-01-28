import scala.compiletime.ops.boolean
@main def hello: Unit = 
  println("Hello world!")
  println(msg)

def msg = "I was compiled by Scala 3. :)"

/**
 * Exercise 1
 */
def pascal(c: Int, r: Int): Int =
  if r <= 0 then 1
  else if c <= 0 || c == r then 1
  else
    pascal(c - 1, r - 1) + pascal(c, r - 1)

/**
 * Exercise 2
 */
def balance(chars: List[Char]): Boolean =
  def innerBalance(stack: List[Char], chars: List[Char]): Boolean = 
    if chars.length == 0 then
      if stack.length == 0 then true
      else false 
    else 
      if chars.head == '(' then innerBalance('('::stack, chars.tail)
      else if chars.head == ')' then 
        if stack.length == 0 then false
        else innerBalance(stack.tail, chars.tail)
      else innerBalance(stack, chars.tail)

  innerBalance(List(), chars)

/**
 * Exercise 3
 */
def countChange(money: Int, coins: List[Int]): Int = 4