package com.diploma.profanity_filter.utils;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PluralsSingulars {
    private static final Set<String> unpluralizables = ImmutableSet.of(
            "equipment", "information", "rice", "money", "species", "series",
            "fish", "sheep", "deer");

    private static final List<Replacer> singularizations = ImmutableList.of(
            replace("(.*)people$").with("$1person"),
            replace("oxen$").with("ox"),
            replace("children$").with("child"),
            replace("feet$").with("foot"),
            replace("teeth$").with("tooth"),
            replace("geese$").with("goose"),
            replace("(.*)ives?$").with("$1ife"),
            replace("(.*)ves?$").with("$1f"),
            replace("(.*)men$").with("$1man"),
            replace("(.+[aeiou])ys$").with("$1y"),
            replace("(.+[^aeiou])ies$").with("$1y"),
            replace("(.+)zes$").with("$1"),
            replace("([m|l])ice$").with("$1ouse"),
            replace("matrices$").with("matrix"),
            replace("indices$").with("index"),
            replace("(.*)ices$").with("$1ex"),
            replace("(octop|vir)i$").with("$1us"),
            replace("(.+(s|x|sh|ch))es$").with("$1"),
            replace("(.+)s$").with("$1")
    );

    private static final List<Replacer> pluralizations = ImmutableList.of(
            replace("(.*)person$").with("$1people"),
            replace("ox$").with("oxen"),
            replace("child$").with("children"),
            replace("foot$").with("feet"),
            replace("tooth$").with("teeth"),
            replace("goose$").with("geese"),
            replace("(.*)fe?$").with("$1ves"),
            replace("(.*)man$").with("$1men"),
            replace("(.+[aeiou]y)$").with("$1s"),
            replace("(.+[^aeiou])y$").with("$1ies"),
            replace("(.+z)$").with("$1zes"),
            replace("([m|l])ouse$").with("$1ice"),
            replace("(.+)(e|i)x$").with("$1ices"),
            replace("(octop|vir)us$").with("$1i"),
            replace("(.+(s|x|sh|ch))$").with("$1es"),
            replace("(.+)").with("$1s" )
    );

    public static String singularize(String word) {
        if (unpluralizables.contains(word.toLowerCase())) {
            return word;
        }

        for (final Replacer singularization : singularizations) {
            if (singularization.matches(word)) {
                return singularization.replace();
            }
        }

        return word;
    }

    public static String pluralize(String word) {
        if (unpluralizables.contains(word.toLowerCase())) {
            return word;
        }

        for (final Replacer pluralization : pluralizations) {
            if (pluralization.matches(word)) {
                return pluralization.replace();
            }
        }

        return word;
    }

    static class Replacer {
        Pattern pattern;
        String replacement;
        Matcher m;

        static class Builder {
            private final Pattern pattern;

            Builder(Pattern pattern) {
                this.pattern = pattern;
            }

            Replacer with(String replacement) {
                return new Replacer(pattern, replacement);
            }
        }

        private Replacer(Pattern pattern, String replacement) {
            this.pattern = pattern;
            this.replacement = replacement;
        }

        boolean matches(String word) {
            m = pattern.matcher(word);
            return m.matches();
        }

        String replace() {
            return m.replaceFirst(replacement);
        }
    }

    static Replacer.Builder replace(String pattern) {
        return new Replacer.Builder(Pattern.compile(pattern));
    }


}
