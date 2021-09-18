assert(isUndef(undef));
assert(!isUndef(true));
assert(!isUndef(false));
assert(!isUndef(1));

assert(!isDef(undef));
assert(isDef(true));
assert(isDef(false));
assert(isDef(1));

$x = undef;
assert($x == undef);

$y = conz();
assert(isDef($y));
assert(isUndef($y.1));
