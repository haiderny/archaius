package netflix.archaius.config;

import java.util.Iterator;
import java.util.LinkedHashSet;

import netflix.archaius.Config;

/**
 * View into another Config for properties starting with a specified prefix.
 * 
 * This class is meant to work with dynamic Config object that may have properties
 * added and removed.
 * 
 * @author elandau
 *
 */
public class PrefixedViewConfig extends AbstractConfig {
    private final Config config;
    private final String prefix;
    
    public PrefixedViewConfig(String prefix, Config config) {
        super(config.getName() + "|" + prefix);
        
        this.config = config;
        this.prefix = prefix.endsWith(".") ? prefix : prefix + ".";
    }

    @Override
    public Iterator<String> getKeys() {
        LinkedHashSet<String> result = new LinkedHashSet<String>();
        Iterator<String> iter = config.getKeys();
        while (iter.hasNext()) {
            String key = iter.next();
            if (key.startsWith(prefix)) {
                result.add(key.substring(prefix.length()));
            }
        }
        return result.iterator();
    }

    @Override
    public boolean containsProperty(String key) {
        return config.containsProperty(prefix + key);
    }

    @Override
    public boolean isEmpty() {
        // TODO: Property implementation
        return config.isEmpty();
    }

    @Override
    public Object getProperty(String key) {
        return config.getProperty(prefix + key);
    }

}
