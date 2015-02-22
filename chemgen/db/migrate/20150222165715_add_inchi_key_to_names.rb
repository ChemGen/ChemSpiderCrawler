class AddInchiKeyToNames < ActiveRecord::Migration
  def change
    add_column :names, :inchi_key, :string
  end
end
