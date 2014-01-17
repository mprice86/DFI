package dfi;

//class to convert ip addresses into values that can be easily incremented
class IPAddress {
    //variable to store value
    private final int value;

    public IPAddress(int value) {
        //constructor
        this.value = value;
    }

    public IPAddress(String stringValue) {
        //split string at each . and store parts in array
        String[] parts = stringValue.split("\\.");
        //if there are not four parts to the address then throw error
        if (parts.length != 4) {
            throw new IllegalArgumentException();
        }
        //stores part to value
        value =
                (Integer.parseInt(parts[0], 10) << (8 * 3)) & 0xFF000000
                | (Integer.parseInt(parts[1], 10) << (8 * 2)) & 0x00FF0000
                | (Integer.parseInt(parts[2], 10) << (8 * 1)) & 0x0000FF00
                | (Integer.parseInt(parts[3], 10) << (8 * 0)) & 0x000000FF;
    }

    public int getOctet(int i) {
        //return value in correct format
        if (i < 0 || i >= 4) {
            throw new IndexOutOfBoundsException();
        }
        return (value >> (i * 8)) & 0x000000FF;
    }

    @Override
    public String toString() {
        //convert IP to string using string builder to append . after each octet
        StringBuilder sb = new StringBuilder();
        for (int i = 3; i >= 0; --i) {
            sb.append(getOctet(i));
            if (i != 0) {
                sb.append(".");
            }
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof IPAddress) {
            return value == ((IPAddress) obj).value;
        }
        return false;
    }

    @Override
    public int hashCode() {
        //return hashcode
        return value;
    }

    public int getValue() {
        //return value
        return value;
    }

    public IPAddress next() {
        //return next IP address
        return new IPAddress(value + 1);
    }
}
