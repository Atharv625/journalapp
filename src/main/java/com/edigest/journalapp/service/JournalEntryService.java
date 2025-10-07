import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edigest.journalapp.entity.User;

@Service
public class JournalEntryService {

    private final JournalEntryRepository journalEntryRepository;
    private final UserService userService;

    @Autowired
    public JournalEntryService(JournalEntryRepository journalEntryRepository, UserService userService) {
        this.journalEntryRepository = journalEntryRepository;
        this.userService = userService;
    }

    // Save or update a journal entry
    @Transactional
    public JournalEntry saveEntry(JournalEntry journalEntry, String userName) {
        User user = userService.findByUserName(userName);
        if (user == null) {
            throw new IllegalArgumentException("User not found: " + userName);
        }

        journalEntry.setDate(LocalDateTime.now());
        journalEntry.setUserId(user.getId()); // associate entry with user
        JournalEntry saved = journalEntryRepository.save(journalEntry);

        // Optionally add to user's journal list if User entity has @DBRef
        if (user.getJournalEntries() != null) {
            user.getJournalEntries().add(saved);
            user.setUserName(null);
            userService.saveUser(user);
        }

        return saved;
    }

    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }

    public void deleteById(ObjectId id) {
        journalEntryRepository.deleteById(id);
    }
}
