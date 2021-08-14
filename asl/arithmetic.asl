assert(1 + 1 == 2);
assert(1 + 2 + 3 == 6);
assert(1 - 2 == -1);
assert(1 - 2 - 3 == -4);
assert(1 + 2 - 3 == 0);

assert(1 + 1.1 == 2.1);
assert(1.2 + 1.1 == 2.3);

assert(2 * 3 == 6);
assert(1 * 3 == 3);
assert(-1 * 3 == -3);
assert(0 * 3.3 == 0.0);
assert(0 * 3 == 0);

assert(2 / 1 == 2);
assert(0 / 2 == 0);
assert(6 / 2 == 3);
assert(-6 / 2 == -3);
assert(6 / -2 == -3);

assert(6 % 4 == 2);
assert(6 % 1 == 0);
assert(1 % 6 == 1);

assert(1 + 2 / 1 + 2 == 5);
assert(1 - 2 / 1 - 2 == -3);
assert(2 * 6 / 3 + 1 == 5);
assert(1 + 2 * 6 / 3 == 5);

defun eqDouble($x1, $x2) {
  $precision = 0.0000000001;
  $delta = $x1 - $x2;
  $delta < $precision && $delta > -$precision;
};

assert(eqDouble(0, 0.0));
assert(eqDouble(1 - 1.1, -0.1));
assert(eqDouble(1.1 * 3, 3.3));
assert(eqDouble(-1.1 / 1, -1.1));
