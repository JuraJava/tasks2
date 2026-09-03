package org.example;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Collection {
    private static class BusinessSuperObject{
        private String value;

        public BusinessSuperObject(String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }

        @Override
        public final boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof BusinessSuperObject)) return false;
            BusinessSuperObject that = (BusinessSuperObject) o;
            return Objects.equals(value, that.value);
        }

        @Override
        public int hashCode() {
            return 1;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Set<BusinessSuperObject> lookup = new HashSet<>();
        lookup.add(new BusinessSuperObject("one"));
        lookup.add(new BusinessSuperObject("two"));
        for (BusinessSuperObject superObject : lookup) {
            System.out.println(superObject.getValue());
        }
    }
}
