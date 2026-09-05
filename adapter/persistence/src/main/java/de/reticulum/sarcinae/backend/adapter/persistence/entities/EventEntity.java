package de.reticulum.sarcinae.backend.adapter.persistence.entities;

import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "event")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Setter
  @Column(name = "name")
  private String name;

  @Setter
  @Column(name = "start_time")
  private OffsetDateTime startTime;

  @Setter
  @Column(name = "end_time")
  private OffsetDateTime endTime;

  @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
  @Builder.Default
  private Set<EventParticipantEntity> participants = new HashSet<>();

  public Set<EventParticipantEntity> getParticipants() {
    return Set.copyOf(participants);
  }

  public void addAllParticipants(Collection<EventParticipantEntity> participants) {
    participants.forEach(this::addParticipant);
  }

  public void addParticipant(EventParticipantEntity participant) {
    participant.setEvent(this);
    this.participants.add(participant);
  }

  public void removeParticipant(EventParticipantEntity participant) {
    this.participants.remove(participant);
  }
}
