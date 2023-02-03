def pascal(c: Int, r: Int): Int = 
  if r <= 0 then 1
  else if c <= 0 || c == r then 1
  else
    pascal(c - 1, r - 1) + pascal(c, r - 1)

pascal(0, 2)
pascal(1, 2)
pascal(1, 3)

val x = 'c'

def balance(chars: List[Char]): Boolean = 
  true



// def countChange(money: Int, coins: List[Int]): Int = 
//   123