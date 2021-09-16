$a1 = conz(a,A);
$a2 = conz(a,A);
assert($a1 != $a2); // не равны, так как два разных объекта - разные ссылки
assert(eqShallow($a1, $a2)); // равны, так как одинаковый набор атрибутов и значений

$x1 = conz(1, $a1);
$x2 = conz(1, $a2);
assert($x1 != $x2);
assert(!eqShallow($x1, $x2)); // не равны, так как значения атрибутов сравниваются по ссылкам
assert(eqDeep($x1, $x2)); // равны, так как структура одинаковая

$s = conz();
$s.a = $s;
assert(eqDeep($s, copyDeep($s)));

$x = conz();
$y = conz(b, $x);
$x.a = $y;
$z = copyDeep($x);
assert($x != $z);
assert(!eqShallow($x, $z));
assert(eqDeep($x, $z));
