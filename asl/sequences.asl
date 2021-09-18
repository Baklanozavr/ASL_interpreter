$x = seqOne(a,b,c,d);
assert($x.start == 1);
assert($x.seqLen == 4);
assert(seqLength($x) == 4);
assert(seqFirst($x) == a);
assert($x[1] == a);
assert($x.1 == a);
assert($x[2] == b);
assert($x.2 == b);
assert($x[3] == c);
assert($x.3 == c);
assert($x[4] == d);
assert($x.4 == d);

$y = seqCopy($x);
assert($y != $x);
assert(eqSeq($x, $y));

$y[1] = z;
assert(!eqSeq($x, $y));
assert($y[1] == z);

$z = seqReverse($x);
assert($z == $x);
assert($x.1 == d);
assert($x.2 == c);
assert($x.3 == b);
assert($x.4 == a);
