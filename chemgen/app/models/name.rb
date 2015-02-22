class Name < ActiveRecord::Base
  belongs_to :compound
  scope :primary, -> {find_by({:primary=>true})}

  def is_primary?
    !!self[:primary]
  end
end
