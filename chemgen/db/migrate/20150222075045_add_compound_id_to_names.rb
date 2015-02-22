class AddCompoundIdToNames < ActiveRecord::Migration
  def change
    add_column :names, :compound_id, :integer
  end
end
