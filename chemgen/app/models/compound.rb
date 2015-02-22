class Compound < ActiveRecord::Base
  has_many :names
  has_many :molfiles

  def is_natural?
    !!self[:natural]
  end

  def molfile
    return nil if self.molfiles.empty?
    self.molfiles.first
  end

  def name
    n = Name.find_by({:inchi_key=>self[:inchi_key]})
    (n.nil?) ? "name" : n.name
  end
end
